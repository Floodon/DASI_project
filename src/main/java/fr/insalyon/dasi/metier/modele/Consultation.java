package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;
import javax.persistence.Column;
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

    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }
    
    public ConsultationState getState() {
        if (this.dateDebut == null) {
            return ConsultationState.EN_ATTENTE;
        } else if (this.dateFin == null) {
            return ConsultationState.DEMARREE;
        } else {
            return ConsultationState.TERMINEE;
        }
    }

    @Override
    public String toString() {
        return "[CONSULTATION " + id + "]"
                + " Date de demande : " + dateDemande + ", date de début : " + dateDebut + ", date de fin : " + dateFin
                + "\n- Client : " + client
                + "\n- Employe : " + employe
                + "\n- Medium : " + medium;
    }
    
    public static enum ConsultationState {
        EN_ATTENTE,
        DEMARREE,
        TERMINEE
    }
    
}