package fr.insalyon.dasi.metier.modele;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Client extends Personne {
    
    /* Attributs */
    
    @Embedded
    @Column(nullable=false)
    private ProfilAstral profilAstral;
    
    /* Constructeurs */

    protected Client() {}

    public Client(ProfilAstral profilAstral, String mail, String motDePasse, String nom, String prenom, String adresse, Date dateNaissance) {
        super(mail, motDePasse, nom, prenom, adresse, dateNaissance);
        this.profilAstral = profilAstral;
    }
    
    /* Methodes */

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }
    
}
