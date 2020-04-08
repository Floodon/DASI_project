package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author berth
 */
@Embeddable
public class ProfilAstral implements Serializable {
    
    /* Attributs */
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable=false)
    private String signeZodiaque;
    @Column(nullable=false)
    private String signeChinois;
    @Column(nullable=false)
    private String Couleur;
    @Column(nullable=false)
    private String Animal;

    /* Constructeurs */
    
    protected ProfilAstral() {}

    public ProfilAstral(String signeZodiaque, String signeChinois, String Couleur, String Animal) {
        this.signeZodiaque = signeZodiaque;
        this.signeChinois = signeChinois;
        this.Couleur = Couleur;
        this.Animal = Animal;
    }
    
    /* Methodes */
    
    public Long getID() {
        return ID;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return Couleur;
    }

    public String getAnimal() {
        return Animal;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleur(String Couleur) {
        this.Couleur = Couleur;
    }

    public void setAnimal(String Animal) {
        this.Animal = Animal;
    }
    
}
