package meilleurtauxiut.examandroidkevincoissard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import meilleurtauxiut.examandroidkevincoissard.Taux.ServiceTaux;
import meilleurtauxiut.examandroidkevincoissard.Taux.Taux;
import meilleurtauxiut.examandroidkevincoissard.Taux.MensualitesUtils;


public class MensualityActivity extends AppCompatActivity {

    MensualitesUtils mensualiteutils = new MensualitesUtils();
    ServiceTaux serviceTaux = new ServiceTaux();
    Taux taux1 = serviceTaux.getTaux10ans();
    Taux taux2 = serviceTaux.getTaux15ans();
    Taux taux3 = serviceTaux.getTaux20ans();

    int valeurPretEnCours = 0;
    TextView TextMontantEnCours;

    //DUREES
    TextView duree1Text;
    int duree1Duree = taux1.getDuree();

    TextView duree2Text;
    int duree2Duree = taux2.getDuree();

    TextView duree3Text;
    int duree3Duree = taux3.getDuree();


    //TAUX
    TextView taux1Text;
    double duree1Valeur = taux1.getValeur();
    TextView taux2Text;
    double duree2Valeur = taux2.getValeur();
    TextView taux3Text;
    double duree3Valeur = taux3.getValeur();

    //MONTANTS
    TextView montant1;
    double montant1Calcule = 0;
    TextView montant2;
    double montant2Calcule = 0;
    TextView montant3;
    double montant3Calcule = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensuality);

        //erreur ici pas eu le temps de corriger
        TextMontantEnCours = (TextView) findViewById(R.id.RecapMontant);

        valeurPretEnCours = Integer.parseInt(TextMontantEnCours.getText().toString());
        TextMontantEnCours.setText("Mensualités possibles pour :" + getIntent().getStringExtra("mytext") + "€");
        //mTextview.setText(getIntent().getStringExtra("mytext"));

        //Remlissage tableau
        miseAJourTableau();

    }

    void calculMontants() {
        this.valeurPretEnCours = Integer.parseInt(TextMontantEnCours.getText().toString());
        //condition validation int/valeur valide à faire après

        //Montant1
        this.montant1Calcule = mensualiteutils.calculMensualite(this.valeurPretEnCours, taux1);
        this.montant1.setText(String.valueOf(this.montant1Calcule));

        //Montant2
        this.montant2Calcule = mensualiteutils.calculMensualite(this.valeurPretEnCours, taux2);
        this.montant2.setText(String.valueOf(this.montant1Calcule));

        //Montant3
        this.montant3Calcule = mensualiteutils.calculMensualite(this.valeurPretEnCours, taux3);
        this.montant3.setText(String.valueOf(this.montant3Calcule));

    }

    void miseAJourTableau() {
        //DUREES
        this.duree1Text.setText(this.duree1Duree);
        this.duree2Text.setText(this.duree2Duree);
        this.duree3Text.setText(this.duree3Duree);

        //TAUX
        this.duree1Text.setText(this.duree1Duree);
        this.duree2Text.setText(this.duree2Duree);
        this.duree3Text.setText(this.duree3Duree);

        calculMontants();
    }
}
