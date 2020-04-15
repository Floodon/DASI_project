/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Personne;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author berth
 */
public class PersonneDao {
    
    public Personne chercherParMail(String mail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Personne> query = em.createQuery("SELECT p FROM Personne p WHERE p.mail = :mail", Personne.class);
        query.setParameter("mail", mail); // correspond au paramètre ":mail" dans la requête
        List<Personne> personnes = query.getResultList();
        Personne result = null;
        if (!personnes.isEmpty()) {
            result = personnes.get(0); // premier de la liste
        }
        return result;
    }
    
}
