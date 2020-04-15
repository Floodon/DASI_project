package fr.insalyon.dasi.metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 */
@Entity
public class Employe extends Personne {
    
    /* Attributs */
    
    @Transient
    private Boolean disponible = true;

    /* Constructeurs */
    
    protected Employe() {
    }

    public Employe(String mail, String motDePasse, String nom, String prenom, String adresse, Date dateNaissance) {
        super(mail, motDePasse, nom, prenom, adresse, dateNaissance);
    }
    
    /* Methode */
    
    public Boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(Boolean d) {
        disponible = d;
    }
    
    public Boolean toggleDisponible() {
        disponible = !disponible;
        return disponible;
    }
    
    @Override
    public String toString() {
        return "[EMPLOYE]" + super.toString();
    }
}
