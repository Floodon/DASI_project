package fr.insalyon.dasi.metier.modele;

import java.util.Date;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 */
@Entity
public class Client extends Personne {
    
    /* Attributs */
    
    @Embedded
    private ProfilAstral profilAstral;
    
    @OneToMany(mappedBy = "client")
    private List<Consultation> mesConsultations;
    
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
    
    public void ajouterConsultation(Consultation consultation) {
        this.mesConsultations.add(consultation);
    }
    
    @Override
    public String toString() {
        return "[CLIENT]" + super.toString() + "\n\t- " + profilAstral;
    }
    
}
