package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.*;
import fr.insalyon.dasi.metier.modele.Personne.Genre;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
//        peuplerBDD();
        testerHistorique();
//        initialiserMediums();
//        testerConsultations();
//        testerInscriptionClient();
//        testerAuthentificationClient();
//        testerRechercheClient();
//        testerListeClients();
//        testerAstroApi();
        JpaUtil.destroy();
    }

    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium);
    }
    
    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }
    
    public static void peuplerBDD() {        
        Medium m1 = new Astrologue("K-Laurie", Genre.FEMME, "K-Laurie lit votre avenir dans votre nourriture !", "2004", "Université de diétologie de Krisp");
        Medium m2 = new Astrologue("Dr. Igeste", Genre.HOMME, "Le Dr. Igeste pourra tout vous dire en ne connaissant que vos légumes !", "2002", "Université de diétologie de Krisp");
        Medium m3 = new Spirite("Mme Irma", Genre.FEMME, "Mme Irma est la plus ancienne de nos spirites. Communication avec l'outremonde garantie !", "Boule de cristal");
        Medium m4 = new Cartomancien("Raoult le magnifique", Genre.HOMME, "Grace à ses notices médicales diverses et variées, Raoult saura tout vous dire sur votre santé à venir");
        Medium m5 = new Cartomancien("Valérie", Genre.FEMME, "Valérie est avant tout notre comptable, mais elle est aussi capable de voir votre avenir économique grace à vos factures.");
        Medium m6 = new Spirite("Lorem", Genre.HOMME, "Ipsum Dolor Amet", "Un site de lorem isum");
        
        Client c1 = new Client("mathieu.chappe@insa-lyon.fr", "Rrrreeee", "Chappe", "Mathieu", Genre.AUTRE, "11 avenue des arts, 69100 Villeurbanne", "0601020304", new Date(70, 0, 1));
        Client c2 = new Client("mathieu.ranzamar@insa-lyon.fr", "C0mpr1s!", "Ranzamar", "Mathieu", Genre.HOMME, "88 rue du n'importe quoi, 31000 Toulouse", "0709080706", new Date(70, 6, 6));
        Client c3 = new Client("guillaume.berthomet@insa-lyon.fr", "DASI", "Berthomet", "Guillaume", Genre.HOMME, "11 avenue Albert Einstein, 69100 Villeurbanne", "0601020304", new Date(98, 4, 8));
        Client c4 = new Client("corentin.forler@insa-lyon.fr", "DASI", "Forler", "Corentin", Genre.HOMME, "11 avenue Albert Einstein, 69100 Villeurbanne", "0601020304", new Date(99, 11, 31));
        Client c5 = new Client("nutella@gmail.com", "Ch0c0-N0isette", "Cajun", "Amandine", Genre.FEMME, "85 rue Lorem Ipsum", "0612345678", new Date(95, 27, 9));
        Client c6 = new Client("idk@hotmail.com", "I really don't know", "Crownguard", "Luxanna", Genre.FEMME, "1 allée des Mageseekers, Demacia", "0606060606", new Date(94, 05, 21));
        Client c7 = new Client("sneaky@gmail.com", "IH4TETianna", "DuCouteau", "Katarina", Genre.FEMME, "Manoir Du Couteau, Noxus", "0688994455", new Date(92, 10, 1));
        
        Employe e1 = new Employe("confiture@gmail.com", "Fr4ise#Cr4nberry", "Vanille", "Clementine", Genre.FEMME, "70 rue Dolor Amet", "0611121314", new Date(92, 4, 3));
        Employe e2 = new Employe("mangue-passion@gmail.com", "1_<3_P1N3apple", "Melba", "Madeleine", Genre.FEMME, "42 impasse Whatever", "0722334455", new Date(89, 12, 12));
        Employe e3 = new Employe("froid@hotmail.fr", "4rcticSoC00l", "Neige", "Vincent", Genre.HOMME, "36 rue du Blizzard, Vancouver", "+1 894 531 25", new Date(95, 3, 19));
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        
        Consultation consult1 = new Consultation(new Date(120, 04, 23, 15, 33, 32), c2, e3, m2);
        Consultation consult2 = new Consultation(new Date(120, 04, 24, 10, 18, 56), c1, e2, m3);
        Consultation consult3 = new Consultation(new Date(120, 04, 24, 17, 9, 02), c3, e3, m4);
        Consultation consult4 = new Consultation(new Date(120, 04, 26, 14, 23, 48), c3, e1, m1);
        Consultation consult5 = new Consultation(new Date(120, 04, 23, 9, 0, 17), c5, e2, m1);
        Consultation consult6 = new Consultation(new Date(120, 04, 27, 13, 19, 42), c2, e2, m3);
        
        consult1.setDateDebut(new Date(120, 4, 23, 15, 48, 9));
        consult1.setDateFin(new Date(120, 4, 23, 16, 03, 53));
        consult1.setCommentaire("Client... Intéressant. Vous pouvez dire un peu ce que vous voulez.");
        
        consult2.setDateDebut(new Date(120, 04, 24, 10, 25, 18));
        consult2.setDateFin(new Date(120, 04, 24, 10, 59, 2));
        consult2.setCommentaire("Pour une fois, un client heureux. Il suffit de lui dire que tout continuera de bien se passer.");
        
        consult3.setDateDebut(new Date(120, 04, 24, 17, 13, 33));
        consult3.setDateFin(new Date(120, 4, 24, 17, 29, 45));
        consult3.setCommentaire("La santé et l'amour vont, mais il a un peu de mal au travail. Appuyez sur des prédictions positives dans ce domaine.");
        
        consult4.setDateDebut(new Date(120, 04, 26, 15, 33, 47));
        consult4.setDateFin(new Date(120, 04, 26, 16, 12, 9));
        consult4.setCommentaire("RAS, consultation très courte.");
        
        consult5.setDateDebut(new Date(120, 04, 23, 9, 5, 1));
        consult5.setDateFin(new Date(120, 04, 23, 9, 49, 7));
        consult5.setCommentaire("Elle a beaucoup aimé la consultation. Prenez un ton calme et posé avec elle.");
        
        consult6.setDateDebut(new Date(120, 04, 27, 13, 22, 42));
        consult6.setDateFin(new Date(120, 04, 27, 13, 34, 42));
        consult6.setCommentaire("Je lui ai dit que sa femme finirait par le quitter... Il est clair qu'il est désespéré.");
        
        try {
            em.getTransaction().begin();
            
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.persist(m5);
            em.persist(m6);
            
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.persist(c5);
            em.persist(c6);
            em.persist(c7);
            
            em.persist(e1);
            em.persist(e2);
            em.persist(e3);
            
            em.persist(consult1);
            em.persist(consult2);
            em.persist(consult3);
            em.persist(consult4);
            em.persist(consult5);
            em.persist(consult6);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            } catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }
    }
    
    public static void testerHistorique() {
        Service service = new Service();
        for (Consultation c : service.listerConsultations("", "", "", new Date(120, 04, 23, 15, 33, 32), new Date(120, 04, 23, 15, 33, 32), new Date(120, 04, 23, 15, 33, 32))) {
            System.out.println(c);
        }
    }
    
    public static void testerAstroApi() {
        System.out.println();
        System.out.println("**** testerAstropApi() ****");
        
        Service service = new Service();
        
        Client c = new Client("mathieu.chappe@insa-lyon.fr", "Rrrreeee", "Chappe", "Mathieu", Genre.HOMME, "11 avenue des arts", "0600000000", new Date(70, 0, 1));
        Client c2 = new Client("mathieu.ranzamar@insa-lyon.fr", "C0mpr1s!", "Ranzamar", "Mathieu", Genre.HOMME, "88 rue du n'importe quoi", "0701010101", new Date(70, 6, 6));
        
        System.out.println(c);
        System.out.println(c2);
        
        try {
            for (String s : service.getPredictions(c, 3, 4, 1)) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println();
    }

    public static void testerConsultations() {
        System.out.println();
        System.out.println("**** testerConsultations() ****");
        
        Service service = new Service();
        
        Medium k = new Astrologue("K-Laurie", Genre.FEMME, "K-Laurie lit votre avenir dans votre nourriture !", "2004", "Université de diétologie de Krisp");
        Medium d = new Astrologue("Dr. Igeste", Genre.HOMME, "Le Dr. Igeste pourra tout vous dire en ne connaissant que vos légumes !", "2002", "Université de diétologie de Krisp");
        Client c = new Client("mathieu.chappe@insa-lyon.fr", "Rrrreeee", "Chappe", "Mathieu", Genre.HOMME, "11 avenue des arts", "0601020304", new Date(70, 0, 1));
        Client c2 = new Client("mathieu.ranzamar@insa-lyon.fr", "C0mpr1s!", "Ranzamar", "Mathieu", Genre.HOMME, "88 rue du n'importe quoi", "0709080706", new Date(70, 6, 6));
        Employe e = new Employe("nutella@gmail.com", "Ch0c0-N0isette", "Cajun", "Amandine", Genre.FEMME, "85 rue Lorem Ipsum", "0612345678", new Date(95, 27, 9));
        Employe e2 = new Employe("confiture@gmail.com", "Fr4ise#Cr4nberry", "Vanille", "Clementine", Genre.FEMME, "70 rue Dolor Amet", "0611121314", new Date(92, 4, 3));
        Employe e3 = new Employe("mangue-passion@gmail.com", "1_<3_P1N3apple", "Melba", "Madeleine", Genre.FEMME, "42 impasse Whatever", "0722334455", new Date(89, 12, 12));
        
        Consultation consult = new Consultation(new Date(), c2, e, k);
        Consultation consult2 = new Consultation(new Date(), c, e, k);
        Consultation consult3 = new Consultation(new Date(), c, e2, k);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            
            em.persist(k);
            em.persist(d);
            em.persist(c);
            em.persist(c2);
            em.persist(e);
            em.persist(e2);
            em.persist(e3);
            
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
        
        System.out.println();
        System.out.println("** Listage des consultations avec filtre **");
        
        List<Consultation> liste = service.listerConsultations(null, null, null, consult.getDateDemande(), null, null);
        liste.forEach((cons) -> {
            System.out.println(cons);
        });
        
        System.out.println();
        System.out.println("** Liste des clients par employés **");
        
        Map<Employe, List<Client>> clientsParEmploye = service.clientsParEmploye();
        clientsParEmploye.forEach((emp, clients) -> 
            System.out.println("- " + emp.getNom() + " " + emp.getPrenom() + " : "
                + clients.stream().map(client -> client.getId() + ", ").reduce("", (s1, s2) -> s1 + s2)
            )
        );
        
        System.out.println();
        System.out.println("** Nombre de consultation par médium **");
        
        Map<Medium, Integer> consultMediums = service.NbrConsultationsParMedium();
        consultMediums.forEach((m, n) -> System.out.println("- " + m.getDenomination() + " : " + n + " consultations"));
        
        System.out.println();
        System.out.println("** Top des médiums **");
        
        List<Medium> topMedium = service.topMedium(5);
        topMedium.forEach(System.out::println);
        
        System.out.println();
        System.out.println("** Lancement et terminaison des consultations **");
        
        Consultation consult4 = service.demanderConsultation(c, k);
        System.out.println(consult4);
        service.lancerConsultation(consult4);
        System.out.println(consult4);
        service.terminerConsultation(consult4, "Ahah commentaire go brrrr");
        System.out.println(consult4);
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
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client claude = new Client("claude.chappe@insa-lyon.fr", "HaCKeR", "Chappe", "Claude", Genre.HOMME, "11 avenue des arts", "0600000000", new Date(70, 0, 1));
        Long idClaude = service.inscrireClient(claude);
        if (idClaude != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(claude);

        Client hedy = new Client("hlamarr@insa-lyon.fr", "WiFi", "Lamarr", "Hedy", Genre.HOMME, "15 avenue Einstein", "0700112233", new Date(75, 6, 15));
        Long idHedy = service.inscrireClient(hedy);
        if (idHedy != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedy);

        Client hedwig = new Client("hem_lamarr@insa-lyon.fr", "WiFi", "Lamarr", "Hedwig Eva Maria", Genre.FEMME, "15 avenue Einstein", "0698876554", new Date(76, 5, 10));
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
