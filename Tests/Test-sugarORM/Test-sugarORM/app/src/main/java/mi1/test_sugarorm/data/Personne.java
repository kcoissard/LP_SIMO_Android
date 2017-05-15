package mi1.test_sugarorm.data;

import com.orm.SugarRecord;

/**
 * Created by fbm on 17/10/16.
 */

public class Personne extends SugarRecord {

    private String nom;
    private String prenom;

    /* SUGARORM : OBLIGATOIRE */
    public Personne(){
    }

    public Personne(String nom, String prenom){
        setNom(nom);
        setPrenom(prenom);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return getPrenom() + " " + getNom();
    }
}
