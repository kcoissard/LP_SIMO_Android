package meilleurtauxiut.examandroidkevincoissard.Taux;

/**
 * Created by Kévin COISSARD on 25/11/2016.
 */
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;

import meilleurtauxiut.examandroidkevincoissard.Taux.ServiceTaux;

public class MensualitesUtils {

    // CONSTANTES
    private static int NOMBRE_DE_MOIS = 12;
    private static int POURCENTAGE_MAX = 100;

    /**
     * Retourne la mensualité en fonction du montant demandé en euros et du taux (exemple : 10 ans et 1.91%)
     *
     * @param montant
     * @param taux
     * @return
     */
    public static double calculMensualite(int montant, Taux taux) {

        //
        double haut;
        double bas;

        // Calcul très scientifique
        // si vous lisez ce commentaire, c'est que vous perdez du temps !
        haut = montant * ((taux.getValeur() / POURCENTAGE_MAX) / NOMBRE_DE_MOIS);
        bas = 1 - Math.pow(1 + ((taux.getValeur() / POURCENTAGE_MAX) / NOMBRE_DE_MOIS), -(taux.getDuree() * NOMBRE_DE_MOIS));

        // Calcul de la mensualité
        double mensualite = ((haut / bas) * POURCENTAGE_MAX) / POURCENTAGE_MAX;

        // Arrondir la mensualité
        BigDecimal mensualiteArrondi = new BigDecimal(mensualite);
        mensualiteArrondi= mensualiteArrondi.setScale(2,BigDecimal.ROUND_DOWN);

        //
        return mensualiteArrondi.doubleValue();
    }
}
