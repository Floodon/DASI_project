/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 */
public class ConsultationDao {
    
    public List<Consultation> listerConsultations(Employe e, Client c, Medium m, Date asked, Date begin, Date end) {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        List<String> conditions = new LinkedList<>();
        if (e != null) {
            conditions.add("c.employe.id = " + e.getId());
        }
        if (c != null) {
            conditions.add("c.client.id = " + c.getId());
        }
        if (m != null) {
            conditions.add("c.medium.id = " + m.getId());
        }
        if (asked != null) {
            conditions.add("c.dateDemande BETWEEN '" + df.format(asked) + ":00:00:00.000' AND '" + df.format(asked) + ":23:59:59.999'");
        }
        if (begin != null) {
            conditions.add("c.dateDebut BETWEEN '" + df.format(begin) + ":00:00:00.000' AND '" + df.format(begin) + ":23:59:59.999'");
        }
        if (end != null) {
            conditions.add("c.dateFin = '" + df.format(end) + ":00:00:00.000' AND '" + df.format(end) + ":23:59:59.999'");
        }
        
        StringBuilder where = new StringBuilder();
        conditions.forEach((cond) -> {
            if (where.length() == 0)
                where.append(" WHERE ");
            else
                where.append(" AND ");
            
            where.append(cond);
        });
        
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c" + where + " ORDER BY c.dateDemande ASC", Consultation.class);
        //System.out.println("\n\n\n" + "SELECT c FROM Consultation c" + where + " ORDER BY c.dateDemande ASC" + "\n\n\n");
        return query.getResultList();
    }
    
    public List<Client> clientsServis(Employe e) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery(
                "SELECT DISTINCT cli FROM Consultation c JOIN c.client cli WHERE c.employe.id = " + e.getId(),
                Client.class
        );
        return query.getResultList();
    }
    
    public Integer nbrConsultationMedium(Medium m) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery(
                "SELECT c FROM Consultation c WHERE c.medium.id = " + m.getId(),
                Consultation.class
        );
        return query.getResultList().size();
    }
    
}
