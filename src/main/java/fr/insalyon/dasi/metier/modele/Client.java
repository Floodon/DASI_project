package fr.insalyon.dasi.metier.modele;

import fr.insalyon.dasi.metier.service.AstroApi;
import java.io.IOException;
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
    
    // Méthode privée pour le constructeur public
    private ProfilAstral recupererProfil()  {
        try {
            return AstroApi.recupererProfil(this);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    public Client(String mail, String motDePasse, String nom, String prenom,
                  Genre genre, String adresse, String telephone, Date dateNaissance) {
        super(mail, motDePasse, nom, prenom, genre, adresse, telephone, dateNaissance);
        profilAstral = recupererProfil();
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
    
    public List<Consultation> getConsultations() {
        return mesConsultations;
    }
    
    @Override
    public String toString() {
        return "[CLIENT]" + super.toString() + "\n\t- " + profilAstral;
    }
    
}
