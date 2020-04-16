package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.PersonneDao;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Personne;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Service {

    /* DAO nécessaires aux services */
    
    protected PersonneDao personneDao = new PersonneDao();
    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected MediumDao mediumDao = new MediumDao();
    protected ConsultationDao consultationDao = new ConsultationDao();

    /* Services d'inscription & authentification */
    
    /**
     * Inscrit un client dans la base de données
     * @param client Le client à inscrire
     * @return L'id du client une fois enregistré ou null si la création a échouée
     */
    public Long inscrireClient(Client client) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    /**
     * Authentifie une personne à l'aide de son mail et de son mot de passe
     * @param mail Le mail de la personne à authentifier
     * @param motDePasse Le mot de passe de la personne à identifier
     * @return L'objet Personne correspondant à la paire mail / mot de passe
     * s'il existe, NULL sinon.
     */
    public Personne authentifierPersonne(String mail, String motDePasse) {
        Personne resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche de la personne : est-ce un employé ?
            Personne p = personneDao.chercherParMail(mail);
            // Vérification du mot de passe
            if (p != null && p.getMotDePasse().equals(motDePasse)) {
                resultat = p;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /* Services de recherche */
    
    /**
     * Fonction générique permettant de rechercher n'importe quelle classe par id
     * en fournissant la fonction adaptée, au sein d'une structure try - catch
     * adaptée.
     */
    private <T> T rechercherParId(Long id, Function<Long, T> searchFunction, Class<T> c) {
        T resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = searchFunction.apply(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherParId(id) - objet de type " + c.getCanonicalName(), ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /**
     * Recherche un client via son id
     * @param id L'id à rechercher
     * @return Le client s'il a été trouvé, NULL sinon
     */
    public Client rechercherClientParId(Long id) {
        return rechercherParId(id, clientDao::chercherParId, Client.class);
    }
    
    /**
     * Recherche un employe via son id
     * @param id L'id à rechercher
     * @return Le employe s'il a été trouvé, NULL sinon
     */
    public Employe rechercherEmployeParId(Long id) {
        return rechercherParId(id, employeDao::chercherParId, Employe.class);
    }
    
    /**
     * Recherche un medium via son id
     * @param id L'id à rechercher
     * @return Le medium s'il a été trouvé, NULL sinon
     */
    public Medium rechercherMediumParId(Long id) {
        return rechercherParId(id, mediumDao::chercherParId, Medium.class);
    }
    
    /* Services fournissant des listes d'éléments */
    
    private <T> List<T> listerObjets(Supplier<List<T>> listFunction, Class<T> c) {
        List<T> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = listFunction.get();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerObjets() - objets de type " + c.getName(), ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /**
     * Crée puis retourne la liste de tous les clients
     * @return La liste de tous les clients dans la base de données
     */
    public List<Client> listerClients() {
        return listerObjets(clientDao::listerClients, Client.class);
    }
    
    /**
     * Crée puis retourne la liste de tous les employes
     * @return La liste de tous les employes dans la base de données
     */
    public List<Employe> listerEmployes() {
        return listerObjets(employeDao::listerEmployes, Employe.class);
    }
    
    /**
     * Crée puis retourne la liste de tous les mediums
     * @return La liste de tous les mediums dans la base de données
     */
    public List<Medium> listerMediums() {
        return listerObjets(mediumDao::listerMediums, Medium.class);
    }
    
    /**
     * Crée puis retourne la liste des consultations filtrés par les champs
     * en paramètre (un paramètre null indique qu'aucun filtre particulier n'est
     * a utiliser pour le champ en question).
     * @param e     Champ employe
     * @param c     Champ client
     * @param m     Champ medium
     * @param asked Date de demande (seule la date est importante, pas le timestamp)
     * @param begin Date de début (seule la date est importante, pas le timestamp)
     * @param end   Date de fin (seule la date est importante, pas le timestamp)
     * @return      Une liste de consultation respectant les critères de recherches.
     */
    public List<Consultation> listerConsultations(Employe e, Client c, Medium m, Date asked, Date begin, Date end) {
        // Lambda expression pour pouvoir utiliser la méthode listerConsultation
        // en paramètre de "listerObjets()"
        return listerObjets(() -> consultationDao.listerConsultations(e, c, m, asked, begin, end), Consultation.class);
    }

}
