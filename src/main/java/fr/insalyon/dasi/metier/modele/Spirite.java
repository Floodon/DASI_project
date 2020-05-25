package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Personne.Genre;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author DASI Team
 */
@Entity
public class Spirite extends Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String support;


    protected Spirite() {
    }

    public Spirite(String denomination, Genre genre, String presentation, String support) {
        super(denomination,genre,presentation);
        this.support = support;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    @Override
    public String getType() {
        return "Spirite";
    }
    
    @Override
    public String toString() {
        return "[SPIRITE]" + super.toString() + ", support : " + support;
    }
    
}
