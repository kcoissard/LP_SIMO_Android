package meilleurtauxiut.examandroidkevincoissard.Taux;

/**
 * Created by Kévin COISSARD on 25/11/2016.
 */

public class Taux {

    private int duree;          // en nombre d'année (exemple : 10)
    private double valeur;      // valeur du taux (exemple : 1.91)

    public Taux(int duree, double valeur) {
        this.duree = duree;
        this.valeur = valeur;
    }

    public int getDuree() {
        return duree;
    }

    public double getValeur() {
        return valeur;
    }
}
