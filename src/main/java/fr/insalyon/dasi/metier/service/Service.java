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
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            // Recherche de la personne : est-ce un client ?
            Personne p = clientDao.chercherParMail(mail);
            if (p != null && p.getMotDePasse().equals(motDePasse)) {
                resultat = p;
            } else { // Sinon, est-ce un employe ?
                p = employeDao.chercherParMail(mail);
                if (p != null && p.getMotDePasse().equals(motDePasse)) {
                    resultat = p;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierPersonne(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /* Services liées aux consultations */
    
    /**
     * Créer une consultation - avec la date actuelle comme date de demande -
     * dont le medium et le client sont précisés. L'employe sera choisi parmi
     * les employes disponibles dont le genre correspond.
     * @param   c Le client ayant demandé la consultation
     * @param   m Le medium demandé pour la consultation
     * @return  La consultation créée, null si aucun employe
     *          valide n'est disponible pour le moment
     */
    public Consultation demanderConsultation(Client c, Medium m) {        
        Consultation resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            resultat = consultationDao.creerConsultationMaintenant(c, m);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderConsultation(consultation)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /**
     * Retourne, si elle existe, la consultation en cours liée à la personne donnée
     * @param p La personne dont on recherche la consultation en cours
     * @return La consultation que la personne effectue actuellement, null si
     * aucune consultation n'est en cours.
     */
    public Consultation obtenirConsultationEnCours(Personne p) {
        Consultation resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = consultationDao.obtenirConsultationEnCours(p);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service obtenirConsultationEnCours(consultation)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /**
     * Permet de passer une consultation au statut "lancée" en inscrivant comme
     * date de début la date actuelle
     * @param c La consultation à démarrer
     * @return true si le statut a pu être modifié, false sinon
     */
    public boolean lancerConsultation(Consultation c) {
        boolean resultat = false;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            resultat = consultationDao.lancerConsultation(c);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service lancerConsultation(consultation)", ex);
            JpaUtil.annulerTransaction();
            resultat = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /**
     * Permet de passer une consultation au statut "terminée" en inscrivant comme
     * date de fin la date actuelle et dans son commentaire un commentaire passé
     * en paramètre.
     * @param c La consultation à terminer
     * @param commentaire Le commentaire de fin de consultation à enregistrer
     * @return true si le statut a pu être modifié, false sinon
     */
    public boolean terminerConsultation(Consultation c, String commentaire) {
        boolean resultat = false;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            resultat = consultationDao.terminerConsultation(c, commentaire);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service terminerConsultation(consultation,commentaire)", ex);
            JpaUtil.annulerTransaction();
            resultat = false;
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
     * @param empName    Champ employe
     * @param clientName Champ client
     * @param medName    Champ medium
     * @param asked      Date de demande (seule la date est importante, pas le timestamp)
     * @param begin      Date de début (seule la date est importante, pas le timestamp)
     * @param end        Date de fin (seule la date est importante, pas le timestamp)
     * @return           Une liste de consultation respectant les critères de recherches.
     */
    public List<Consultation> listerConsultations(String empName, String clientName, String medName, Date asked, Date begin, Date end) {
        // Lambda expression pour pouvoir utiliser la méthode listerConsultation
        // en paramètre de "listerObjets()"
        return listerObjets(() -> consultationDao.listerConsultations(empName, clientName, medName, asked, begin, end), Consultation.class);
    }
    
    /**
     * Crée puis retourne la liste des consultations filtrés par les champs
     * en paramètre (un paramètre null indique qu'aucun filtre particulier n'est
     * a utiliser pour le champ en question).
     * @param e Champ employe
     * @param c Champ client
     * @param m Champ medium
     * @return  Une liste de consultation respectant les critères de recherches.
     */
    public List<Consultation> listerConsultations(Employe e, Client c, Medium m) {
        // Lambda expression pour pouvoir utiliser la méthode listerConsultation
        // en paramètre de "listerObjets()"
        return listerObjets(() -> consultationDao.listerConsultations(e, c, m), Consultation.class);
    }

    /* Autres services */
    
    /**
     * Renvoie une map associant à chaque employe la liste des clients qu'il a servi
     * @return Une map Employe - Liste de client ou null en cas d'erreur
     */
    public Map<Employe, List<Client>> clientsParEmploye() {
        Map<Employe, List<Client>> resultat = new HashMap<>();
        
        JpaUtil.creerContextePersistance();
        try {
            List<Employe> listeEmployes = employeDao.listerEmployes();
            for (Employe e : listeEmployes) {
                resultat.put(e, consultationDao.clientsServis(e));
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service clientsParEmploye()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return resultat;
    }
    
    /**
     * Renvoie le nombre de consultations que le medium m a effectué
     * @param m Le médium dont on veut connaître le nombre de consultations effectuées
     * @return Le nombre de consultations effectuées par m
     */
    public Integer nbrConsultations(Medium m) {
        Integer resultat = 0;
        
        JpaUtil.creerContextePersistance();
        try {
            resultat = consultationDao.nbrConsultationMedium(m);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service NbrConsultationsParMedium()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return resultat;
    }
    
    /**
     * Renvoie une map associant à chaque medium le nombre de consultation qu'il
     * a "effectue".
     * @return Une map Medium - Nombre de consultation ou null en cas d'erreur
     */
    public Map<Medium, Integer> NbrConsultationsParMedium() {
        Map<Medium, Integer> resultat = new HashMap<>();
        
        JpaUtil.creerContextePersistance();
        try {
            List<Medium> listeMediums = mediumDao.listerMediums();
            for (Medium m : listeMediums) {
                resultat.put(m, consultationDao.nbrConsultationMedium(m));
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service NbrConsultationsParMedium()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return resultat;
    }
    
    /**
     * Renvoie une liste triée des nbrToKeep mediums les plus demandés (càd ceux
     * qui ont le plus de consultation les référençant), triée dans l'ordre
     * décroissant.
     * @param nbrToKeep Nombre de mediums à conserver dans la liste
     * @return Une listre triée dans l'ordre décroissant du nombre de consultation
     * et tronquée à nbrToKeep mediums.
     */
    public List<Medium> topMedium(int nbrToKeep) {
        List<Medium> resultat = null;
        
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMediums();
            resultat.sort((m1, m2) -> consultationDao.nbrConsultationMedium(m2) - consultationDao.nbrConsultationMedium(m1));
            nbrToKeep = nbrToKeep > resultat.size() ? resultat.size() : nbrToKeep;
            resultat = resultat.subList(0, nbrToKeep);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service topMedium()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return resultat;
    }
    
    /* Astro API */

    /**
     * Permet de récupérer des prédictions automatisées selon les niveaux en amour,
     * santé et travail d'un client.
     * 
     * @param client Le client pour lequel on doit faire une "prédiction personnalisée"
     * @param amour Son niveau d'amour (1 à 4)
     * @param sante Son niveau de santé (1 à 4)
     * @param travail Son niveau de travail (1 à 4)
     * @return Une liste de trois string : la prédiction en amour, la prédiction
     * en sante puis la prédiction en travail.
     * @throws IOException 
     */
    public List<String> getPredictions(Client client, int amour, int sante, int travail) throws IOException {
        return AstroApi.getPredictions(client, amour, sante, travail);
    }
    
}
