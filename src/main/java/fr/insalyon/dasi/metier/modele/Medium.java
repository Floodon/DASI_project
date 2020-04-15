package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fr.insalyon.dasi.metier.modele.Personne.Genre;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

/**
 *
 * @author DASI Team
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Medium implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String denomination;
    @Column(nullable=false)
    private Genre genre;
    @Lob // Large data (+ grand que VARCHAR)
    @Column(nullable=false)
    private String presentation;


    protected Medium() {
    }

    public Medium(String denomination, Genre genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
    }

    public String getDenomination() {
        return denomination;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
    
    

    @Override
    public String toString() {
        return "[MEDIUM " + id + "] Nom : " + denomination + ", genre : " + genre;
    }
    

}
