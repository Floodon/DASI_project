package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fr.insalyon.dasi.metier.modele.Personne.Genre;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;

/**
 *
 * @author DASI Team
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Consultation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column()
    private String commentaire;
    
    @Column()
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp dateDemande;
    
    @Column()
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp dateDebut;
    
    @Column()
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp dateFin;


    protected Consultation() {
        
    }

    public Consultation(Timestamp dateDemande) {
        this.commentaire = null;
        this.dateDemande = dateDemande;
        this.dateDebut = null;
        this.dateFin = null;
    }

    /* Getters et Setters */
    
    public Long getId() {
        return id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Timestamp getDateDemande() {
        return dateDemande;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }
    
    public ConsultationState getState() {
        if (this.dateDebut == null) {
            return ConsultationState.EnAttente;
        } else if (this.dateFin == null) {
            return ConsultationState.Démarrée;
        } else {
            return ConsultationState.Terminée;
        }
    }

    @Override
    public String toString() {
        return "Consultation TOSTRING";
    }
}


enum ConsultationState {
    EnAttente,
    Démarrée,
    Terminée
}