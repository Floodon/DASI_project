package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 */
public class ClientDao {
    
    public void creer(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(client);
    }
    
    public Client chercherParId(Long clientId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Client.class, clientId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Client chercherParMail(String clientMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c, Personne p WHERE p.mail = :mail AND c.id = p.id", Client.class);
        query.setParameter("mail", clientMail); // correspond au paramètre ":mail" dans la requête
        List<Client> clients = query.getResultList();
        Client result = null;
        if (!clients.isEmpty()) {
            result = clients.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Client> listerClients() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c, Personne p WHERE c.id = p.id ORDER BY p.nom ASC, p.prenom ASC", Client.class);
        return query.getResultList();
    }
    
}
