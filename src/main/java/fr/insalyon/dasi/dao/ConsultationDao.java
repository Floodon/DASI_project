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
        // TODO comprendre le bug...
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        List<String> conditions = new LinkedList<>();
        if (e != null)
            conditions.add("c.employe_id = " + e.getId());
        if (c != null)
            conditions.add("c.client_id = " + c.getId());
        if (m != null)
            conditions.add("c.medium_id = " + m.getId());
        if (asked != null)
            conditions.add("c.datedemande = '" + df.format(asked) + "'");
        if (begin != null)
            conditions.add("c.datedebut = '" + df.format(begin) + "'");
        if (end != null)
            conditions.add("c.datefin = '" + df.format(end) + "'");
        
        String where = "";
        for (String cond : conditions) {
            if (where.equals(""))
                where = "WHERE ";
            else
                where += " AND ";
            
            where += cond;
        }
        
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM consultation c " + where + " ORDER BY c.datedemande ASC", Consultation.class);
        return query.getResultList();
    }
    
}
