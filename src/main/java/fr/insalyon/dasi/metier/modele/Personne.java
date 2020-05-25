package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DASI Team
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne implements Serializable {

    /* Attributs */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable=false)
    private String mail;
    @Column(nullable=false)
    private String motDePasse;
    
    @Column(nullable=false)
    private String nom;
    @Column(nullable=false)
    private String prenom;
    @Column(nullable=false)
    private Genre genre;
    @Column(nullable=false)
    private String adresse;
    @Column(nullable=false)
    private String telephone;
    @Column(nullable=false)
    @Temporal(value=TemporalType.DATE)
    private Date dateNaissance;

    /* Constructeurs */
    
    protected Personne() {
    }

    public Personne(String mail, String motDePasse, String nom, String prenom,
                    Genre genre, String adresse, String telephone, Date dateNaissance) {
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.adresse = adresse;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
    }

    /* Methodes */
    
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "[PERSONNE " + id + "], nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse
                + ", date de naissance=" + dateNaissance + ", adresse=" + adresse + ", telephone=" + telephone;
    }
    
    /* Enumerations */
    
    public static enum Genre {
        FEMME,
        HOMME,
        AUTRE
    }
}
