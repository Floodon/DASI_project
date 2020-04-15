package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Personne.Genre;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author DASI Team
 */
@Entity
public class Cartomancien extends Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    protected Cartomancien() {
    }

    public Cartomancien(String denomination, Genre genre, String presentation) {
        super(denomination,genre,presentation);
    }

    @Override
    public String toString() {
        return "[CARTOMANCIEN]" + super.toString();
    }
    

}

