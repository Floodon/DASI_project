package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author berth
 */
@Embeddable
public class ProfilAstral implements Serializable {
    
    /* Attributs */
    
    @Column(nullable=false)
    private String signeZodiaque;
    @Column(nullable=false)
    private String signeChinois;
    @Column(nullable=false)
    private String couleur;
    @Column(nullable=false)
    private String animal;

    /* Constructeurs */
    
    protected ProfilAstral() {}

    public ProfilAstral(String signeZodiaque, String signeChinois, String Couleur, String Animal) {
        this.signeZodiaque = signeZodiaque;
        this.signeChinois = signeChinois;
        this.couleur = Couleur;
        this.animal = Animal;
    }
    
    /* Methodes */

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
    
    @Override
    public String toString() {
        return "[PROFIL ASTRAL] " + signeZodiaque + " / " + signeChinois + " / " + couleur + " / " + animal;
    }    
}
