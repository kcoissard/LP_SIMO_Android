package meilleurtauxiut.examandroidkevincoissard.Taux;

/**
 * Created by Kévin COISSARD on 25/11/2016.
 */

import java.util.ArrayList;
import java.util.List;

import meilleurtauxiut.examandroidkevincoissard.Taux.Taux;

public class ServiceTaux {



    // Les appels au serveur sont simulés dans cette classe
    private static Taux TAUX_10_ANS = new Taux(10, 1.91);
    private static Taux TAUX_15_ANS = new Taux(15, 2.16);
    private static Taux TAUX_20_ANS = new Taux(20, 2.41);
    private static Taux TAUX_25_ANS = new Taux(25, 2.7);


    /**
     * Retourne le taux d'emprunt sur 10 ans (par exemple 1.91)
     *
     * @return
     */
    public static Taux getTaux10ans() {

        // Normalement appel à un serveur externe
        // Ici on simule le retour des resultats
        return TAUX_10_ANS;
    }

    /**
     * Retourne le taux d'emprunt sur 15 ans (par exemple 2.16)
     *
     * @return
     */
    public static Taux getTaux15ans() {

        // Normalement appel à un serveur externe
        // Ici on simule le retour des resultats
        return TAUX_15_ANS;
    }

    /**
     * Retourne le taux d'emprunt sur 20 ans (par exemple 2.41)
     *
     * @return
     */
    public static Taux getTaux20ans() {

        // Normalement appel à un serveur externe
        // Ici on simule le retour des resultats
        return TAUX_20_ANS;
    }

}
