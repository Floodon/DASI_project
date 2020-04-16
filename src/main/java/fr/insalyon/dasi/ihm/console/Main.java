package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.*;
import fr.insalyon.dasi.metier.modele.Personne.Genre;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        JpaUtil.init();
//        initialiserMediums();
        testerConsultations();
//        testerInscriptionClient();
//        testerAuthentificationClient();
//        testerRechercheClient();
//        testerListeClients();
        JpaUtil.destroy();
    }

    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium);
    }
    
    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }

    public static void testerConsultations() {
        System.out.println();
        System.out.println("**** initialiserConsultations() ****");
        System.out.println();
        
        Service service = new Service();
        
        ProfilAstral test = new ProfilAstral("Gémeau", "Dragon", "Orange", "Ornythorinque");
        
        Medium k = new Astrologue("K-Laurie", Genre.FEMME, "K-Laurie lit votre avenir dans votre nourriture !", "2004", "Université de diétologie de Krisp");
        Client c = new Client(test, "mathieu.chappe@insa-lyon.fr", "Rrrreeee", "Chappe", "Mathieu", "11 avenue des arts", new Date(70, 0, 1));
        Client c2 = new Client(test, "mathieu.ranzamar@insa-lyon.fr", "C0mpr1s!", "Ranzamar", "Mathieu", "88 rue du n'importe quoi", new Date(70, 6, 6));
        Employe e = new Employe("nutella@gmail.com", "Ch0c0-N0isette", "Cajun", "Amandine", "85 rue Lorem Ipsum", new Date(95, 27, 9));
        Employe e2 = new Employe("confiture@gmail.com", "Fr4ise#Cr4nberry", "Vanille", "Clementine", "70 rue Dolor Amet", new Date(92, 4, 3));
        
        Consultation consult = new Consultation(new Date(), c2, e, k);
        Consultation consult2 = new Consultation(new Date(), c, e, k);
        Consultation consult3 = new Consultation(new Date(), c, e2, k);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            
            em.persist(k);
            em.persist(c);
            em.persist(c2);
            em.persist(e);
            em.persist(e2);
            
            em.persist(consult);
            em.persist(consult2);
            em.persist(consult3);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
        
        List<Consultation> liste = service.listerConsultations(null, null, null, consult.getDateDemande(), null, null);
        liste.forEach((cons) -> {
            System.out.println(cons);
        });
    }
    
    public static void initialiserMediums() {
        
        System.out.println();
        System.out.println("**** initialiserMediums() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        Spirite tran = new Spirite("Professeur Tran",Genre.HOMME, "Votre avenir est devant vous: regardons-le ensemble!", "Marc de café, boule de cristal, oreilles de lapin");
        Cartomancien irma = new Cartomancien("Mme Irma", Genre.FEMME, "Passionnée de carte Yu-Gi-Oh! depuis sa tendre enfance, Mme Irma sait y faire avec les cartes.");
        Astrologue martinetto = new Astrologue("Martinetto", Genre.HOMME, "Martinetto est le médium voyance qu'il vous faut! Diplômé de la faculté de quantimnique mézospirituelle de Arvarde et membre honorifique de la Hauta Académie des psiences de Zingapour, il saura se connecter avec les anciens et ainsi établir le pont quantique nécessaire pour que vous puissiez discuter avec vos proches disparus.", "1999", "Faculté de quantimnique mézospirituelle de Arvarde");
        System.out.println();
        System.out.println("** Mediums avant persistance: ");
        afficherMedium(tran);
        afficherMedium(irma);
        afficherMedium(martinetto);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(tran);
            em.persist(irma);
            em.persist(martinetto);
            em.getTransaction().commit();
            
            System.out.println();
            System.out.println("** Mediums après persistance: ");
            afficherMedium(tran);
            afficherMedium(irma);
            afficherMedium(martinetto);
            System.out.println();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
    }

    public static void testerInscriptionClient() {
        
        ProfilAstral test = new ProfilAstral("Gémeau", "Dragon", "Orange", "Ornythorinque");
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client claude = new Client(test, "claude.chappe@insa-lyon.fr", "HaCKeR", "Chappe", "Claude", "11 avenue des arts", new Date(70, 0, 1));
        Long idClaude = service.inscrireClient(claude);
        if (idClaude != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(claude);

        Client hedy = new Client(test, "hlamarr@insa-lyon.fr", "WiFi", "Lamarr", "Hedy", "15 avenue Einstein", new Date(75, 6, 15));
        Long idHedy = service.inscrireClient(hedy);
        if (idHedy != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedy);

        Client hedwig = new Client(test, "hem_lamarr@insa-lyon.fr", "WiFi", "Lamarr", "Hedwig Eva Maria", "15 avenue Einstein", new Date(76, 5, 10));
        Long idHedwig = service.inscrireClient(hedwig);
        if (idHedwig != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedwig);
    }

    public static void testerRechercheClient() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Client client;

        id = 1;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client #" + id + " non-trouvé");
        }
    }

    public static void testerListeClients() {
        
        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();
        
        Service service = new Service();
        List<Client> listeClients = service.listerClients();
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            listeClients.forEach((client) -> {
                afficherClient(client);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerAuthentificationClient() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();
        
        Service service = new Service();
        Personne personne;
        Client client;
        String mail;
        String motDePasse;

        mail = "claude.chappe@insa-lyon.fr";
        motDePasse = "HaCKeR";
        personne = service.authentifierPersonne(mail, motDePasse);
        client = personne == null ? null : service.rechercherClientParId(personne.getId());
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "claude.chappe@insa-lyon.fr";
        motDePasse = "HaCkeR";
        personne = service.authentifierPersonne(mail, motDePasse);
        client = personne == null ? null : service.rechercherClientParId(personne.getId());
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "etudiant.fictif@insa-lyon.fr";
        motDePasse = "********";
        personne = service.authentifierPersonne(mail, motDePasse);
        client = personne == null ? null : service.rechercherClientParId(personne.getId());
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }

    /*public static void saisirInscriptionClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("Appuyer sur Entrée pour passer la pause...");
        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** NOUVELLE INSCRIPTION **");
        System.out.println("**************************");
        System.out.println();

        String nom = Saisie.lireChaine("Nom ? ");
        String prenom = Saisie.lireChaine("Prénom ? ");
        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");

        Client client = new Client(nom, prenom, mail, motDePasse);
        Long idClient = service.inscrireClient(client);

        if (idClient != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(client);

    }

    public static void saisirRechercheClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("*********************");
        System.out.println("** MENU INTERACTIF **");
        System.out.println("*********************");
        System.out.println();

        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** RECHERCHE de CLIENTS **");
        System.out.println("**************************");
        System.out.println();
        System.out.println();
        System.out.println("** Recherche par Identifiant:");
        System.out.println();

        Integer idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        while (idClient != 0) {
            Client client = service.rechercherClientParId(idClient.longValue());
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client #" + idClient + " non-trouvé");
            }
            System.out.println();
            idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("********************************");
        System.out.println("** AUTHENTIFICATION de CLIENT **");
        System.out.println("********************************");
        System.out.println();
        System.out.println();
        System.out.println("** Authentifier Client:");
        System.out.println();

        String clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");

        while (!clientMail.equals("0")) {
            String clientMotDePasse = Saisie.lireChaine("Mot de passe ? ");
            Client client = service.authentifierClient(clientMail, clientMotDePasse);
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client non-authentifié");
            }
            clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("*****************");
        System.out.println("** AU REVOIR ! **");
        System.out.println("*****************");
        System.out.println();

    }*/
}
