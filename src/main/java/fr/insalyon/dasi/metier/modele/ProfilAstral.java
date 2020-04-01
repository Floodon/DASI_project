package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author berth
 */
@Entity
public class ProfilAstral implements Serializable {
    
    /* Attributs */
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable=false)
    private SigneZodiaque signeZodiaque;
    @Column(nullable=false)
    private SigneChinois signeChinois;
    @Column(nullable=false)
    private String Couleur;
    @Column(nullable=false)
    private String Animal;

    /* Constructeurs */
    
    protected ProfilAstral() {}

    public ProfilAstral(SigneZodiaque signeZodiaque, SigneChinois signeChinois, String Couleur, String Animal) {
        this.signeZodiaque = signeZodiaque;
        this.signeChinois = signeChinois;
        this.Couleur = Couleur;
        this.Animal = Animal;
    }
    
    /* Methodes */
    
    public Long getID() {
        return ID;
    }

    public SigneZodiaque getSigneZodiaque() {
        return signeZodiaque;
    }

    public SigneChinois getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return Couleur;
    }

    public String getAnimal() {
        return Animal;
    }

    public void setSigneZodiaque(SigneZodiaque signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(SigneChinois signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleur(String Couleur) {
        this.Couleur = Couleur;
    }

    public void setAnimal(String Animal) {
        this.Animal = Animal;
    }
    
    /* Enumeration */
    
    public static enum SigneZodiaque {
        BELIER,
        TAUREAU,
        GEMEAUX,
        CANCER,
        LION,
        VIERGE,
        BALANCE,
        SCORPION,
        SAGITTAIRE,
        CAPRICORNE,
        VERSEAU,
        POISSONS
    }
    
    public static enum SigneChinois {
        RAT,
        BOEUF,
        TIGRE,
        LIEVRE,
        DRAGON,
        SERPENT,
        CHEVAL,
        CHEVRE,
        SINGE,
        COQ,
        CHIEN,
        COCHON
    }
    
}
