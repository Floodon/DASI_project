package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.modele.Personne.Genre;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 */
@Entity
public class Astrologue extends Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String promotion;
    @Column(nullable=false)
    private String formation;


    protected Astrologue() {
    }

    public Astrologue(String denomination, Genre genre, String presentation, String promotion, String formation) {
        super(denomination,genre,presentation);
        this.promotion = promotion;
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getFormation() {
        return formation;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    @Override
    public String getType() {
        return "Astrologue";
    }
    
    @Override
    public String toString() {
        return "[ASTROLOGUE]" + super.toString() + ", formation : " + formation + ", promotion : " + promotion;
    }
    

}
