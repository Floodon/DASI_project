package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author DASI Team
 */
@Entity
public class Consultation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column()
    private String commentaire;
    
    @Column()
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateDemande;
    
    @Column()
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateDebut;
    
    @Column()
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateFin;
    
    @ManyToOne
    private Client client;
    
    @ManyToOne 
    private Employe employe;
            
    @ManyToOne 
    private Medium medium;


    protected Consultation() {
        
    }

    public Consultation(Date dateDemande) {
        this.commentaire = null;
        this.dateDemande = dateDemande;
        this.dateDebut = null;
        this.dateFin = null;
    }
    
    public Consultation(Date dateDemande, Client client, Employe employe, Medium medium) {
        this.commentaire = null;
        this.dateDemande = dateDemande;
        this.dateDebut = null;
        this.dateFin = null;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
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

    public Date getDateDemande() {
        return dateDemande;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
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