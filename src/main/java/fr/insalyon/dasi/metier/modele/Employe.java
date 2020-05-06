package fr.insalyon.dasi.metier.modele;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Employe extends Personne {

    /* Constructeurs */
    
    protected Employe() {
    }

    public Employe(String mail, String motDePasse, String nom, String prenom, Genre genre, String adresse, Date dateNaissance) {
        super(mail, motDePasse, nom, prenom, genre, adresse, dateNaissance);
    }
    
    /* Methode */
    
    @Override
    public String toString() {
        return "[EMPLOYE]" + super.toString();
    }
}
