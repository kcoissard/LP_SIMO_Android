package tabata.sporttimerbis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Button;

//import android.graphics.drawable.RippleDrawable;
//import android.support.v7.widget.AppCompatImageHelper;
//import java.io.Serializable;



public class MainActivity extends AppCompatActivity {

    //GETTERS
    public int getNbCycles() {
        return nbCycles;
    }

    public int getSecTempsEffort() {
        return secTempsEffort;
    }

    public int getSecTempsPause() {
        return secTempsPause;
    }

    public int getSecTempsTotal() {
        return secTempsTotal;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public long getValTimeToSave() {
        return valTimeToSave;
    }

    public CountDownTimer getTimer() {
        return timer;
    }


    // DATA
    private int nbCycles = 1;
    private int secTempsEffort = 0;
    private int secTempsPause = 0;
    private int secTempsTotal = 0;

    // VIEW
    TextView valeurCycles;
    TextView valeurTempsEffort;
    TextView valeurTempsPause;
    TextView valeurTempsTotal;


    // VIEW timer
    private Button startButton;
    private Button pauseButton;
    TextView timerValue;

    //ACTIVITE RECREE
    private boolean nouvelleActivite=true;


    // DATA timer
    private long updatedTime=0; // VALEUR A CALCULER
    private long valTimeToSave=0;

    //TIMER
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //pour ne pas avoir le nom de l'appli en haut
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //on récupère le texte du nombre de cycles
        valeurCycles = (TextView) findViewById(R.id.valeurCycles);


        //on récupère le texte du temps d'effort
        valeurTempsEffort = (TextView) findViewById(R.id.valeurTempsEffort);

        //on récupère le texte du temps de pause
        valeurTempsPause = (TextView) findViewById(R.id.valeurTempsPause);

        //on récupère le texte du temps total du tabata
        valeurTempsTotal = (TextView) findViewById(R.id.valeurTempsTotal);


        // Récupérer les view et boutons du timer
        timerValue = (TextView) findViewById(R.id.timerValue);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);


        if(savedInstanceState!=null)
        {
            //Pour préciser à MAJ que le temps total doit se baser sur le temps update recupéré
            nouvelleActivite=false;

            //SI Le timer tournait, on le met en pause
            pauseButton.setText("Reprendre");

            nbCycles=savedInstanceState.getInt("nbCycles", 1);

            valeurCycles.setText(savedInstanceState.getString("valeurCycles", "erreur"));

            secTempsEffort=savedInstanceState.getInt("secTempsEffort", 0);
            valeurTempsEffort.setText(savedInstanceState.getString("valeurTempsEffort", "erreur"));

            secTempsPause=savedInstanceState.getInt("secTempsPause", 0);
            valeurTempsPause.setText(savedInstanceState.getString("valeurTempsPause", "erreur"));

            startButton.setText(savedInstanceState.getString("boutonCommencer", "erreur"));

            secTempsTotal=savedInstanceState.getInt("secTempsTotal", 0);
            this.updatedTime=savedInstanceState.getLong("updatedTime", 0);
            valTimeToSave=savedInstanceState.getLong("valTimeToSave", 0);
        }

        miseAJour();
    }


    //CYCLES
    //EVENEMENT BOUTON +
    public void onClickBoutonPlusCycles(View v){
        if(this.nbCycles<20) {
            this.nbCycles = nbCycles + 1;

            //mise à jour du nombre de cycles
            valeurCycles.setText(Integer.toString(nbCycles));

            //mise à jour du temps total
            miseAJour();
        }
    }

    //EVENEMENT BOUTON -
    public void onClickBoutonMoinsCycles(View v){
        if(this.nbCycles>1) {
            this.nbCycles = nbCycles - 1;

            //mise à jour du nombre de cycles
            valeurCycles.setText(Integer.toString(nbCycles));

            //mise à jour du temps total
            miseAJour();
        }
    }


    //TEMPS D'EFFORT
    //EVENEMENT BOUTON +
    public void onClickBoutonPlusTempsEffort(View v){
        if(this.secTempsEffort<120) {
            this.secTempsEffort = secTempsEffort + 1;

            //mise à jour du nombre de secondes temps Effort
            valeurTempsEffort.setText(Integer.toString(secTempsEffort));

            //mise à jour du temps total
            miseAJour();
        }
    }

    //EVENEMENT BOUTON -
    public void onClickBoutonMoinsTempsEffort(View v){
        if(this.secTempsEffort>0) {
            this.secTempsEffort = secTempsEffort - 1;

            //mise à jour du nombre de secondes temps Effort
            valeurTempsEffort.setText(Integer.toString(secTempsEffort));

            //mise à jour du temps total
            miseAJour();
        }
    }


    //TEMPS DE PAUSE
    //EVENEMENT BOUTON +
    public void onClickBoutonPlusTempsPause(View v){
        if(this.secTempsPause<120) {
            this.secTempsPause = secTempsPause + 1;

            //mise à jour du nombre de secondes temps Pause
            valeurTempsPause.setText(Integer.toString(secTempsPause));

            //mise à jour du temps total
            miseAJour();
        }
    }

    //EVENEMENT BOUTON -
    public void onClickBoutonMoinsTempsPause(View v){
        if(this.secTempsPause>0) {
            this.secTempsPause = secTempsPause - 1;

            //mise à jour du nombre de secondes temps Pause
            valeurTempsPause.setText(Integer.toString(secTempsPause));

            //mise à jour du temps total
            miseAJour();
        }
    }

    // BOUTON START
    public void onStart(View view) {

        //Si le timer est lancé on laisse la possibilité de STOP et Reset
        String chaineBoutonStart = startButton.getText().toString();

        //updatedTime de base
        this.valTimeToSave = this.updatedTime;

        if(this.secTempsTotal!=0 && !chaineBoutonStart.equals("Stop")) {

            //on met à jour la valeur d'updatedTime sinon MAJ le met pas à jour si == 0
            this.updatedTime = this.secTempsTotal * 1000;

            startButton.setText("Stop");

            if (timer == null) {
                timer = new CountDownTimer(updatedTime, 10) {

                    public void onTick(long millisUntilFinished) {
                        updatedTime = millisUntilFinished;

                        //MISE A JOUR DE L'AFFICHAGE DU TIMER
                        int minutesTempsTotal;
                        int secondesTempstotal;

                        // Décompositions en secondes et minutes
                        secondesTempstotal = (int) (updatedTime / 1000);
                        minutesTempsTotal = secondesTempstotal / 60;
                        secondesTempstotal = secondesTempstotal % 60;

                        String helpAffichMins="";
                        if(minutesTempsTotal<10){helpAffichMins="0";}

                        // Affichage du "timer"
                        timerValue.setText("" + helpAffichMins + minutesTempsTotal + ":"
                                + String.format("%02d", secondesTempstotal));
                    }

                    public void onFinish() {
                        updatedTime = 0;
                        // Affichage du "timer"
                        timerValue.setText("00:00");
                        timer = null;
                        updatedTime = valTimeToSave;
                        startButton.setText("Recommencer");
                    }
                }.start();
            }

            //SI le bouton était sur stop, c'est que l'on veut tout arreter et recommencer
        } else if (chaineBoutonStart.equals("Stop")) {

                startButton.setText("Commencer");
                pauseButton.setText("Pause");

                this.nbCycles = 1;
                valeurCycles.setText(Integer.toString(nbCycles));

                this.secTempsEffort = 0;
                valeurTempsEffort.setText(Integer.toString(secTempsEffort));

                this.secTempsPause = 0;
                valeurTempsPause.setText(Integer.toString(secTempsPause));

                this.secTempsTotal = 0;
                valeurTempsTotal.setText("00:00");

                // on met le timer à 0
                updatedTime = 0;
                // Affichage du "timer"
                timerValue.setText("00:00");

                // on annule le timer en cours
                if(timer!=null)
                {
                    timer.cancel();
                    timer = null;
                }

                //On recommence toute l'activité
                nouvelleActivite=true;

                //on sort de la fonction une fois que l'on a tout vidé
                return;
        }
    }

    // BOUTON PAUSE
    public void onPause(View view) {
        String chaineBoutonPause = pauseButton.getText().toString();

        //LE TIMER TOURNE, ON VEUT L'ARRETER
        if (timer != null && chaineBoutonPause.equals("Pause")) {
            //on change le text du bouton à chaque clique sur pause

            pauseButton.setText("Reprendre");
            timer.cancel();
        }
        //LE TIMER EST ARRETE
        else if(chaineBoutonPause.equals("Reprendre"))
        {
            pauseButton.setText("Pause");
            timer = new CountDownTimer(this.updatedTime, 10) {

                public void onTick(long millisUntilFinished) {
                    updatedTime = millisUntilFinished;

                    //MISE A JOUR DE L'AFFICHAGE DU TIMER
                    int minutesTempsTotal;
                    int secondesTempstotal;

                    // Décompositions en secondes et minutes
                    secondesTempstotal = (int) (updatedTime / 1000);
                    minutesTempsTotal = secondesTempstotal / 60;
                    secondesTempstotal = secondesTempstotal % 60;

                    String helpAffichMins="";
                    if(minutesTempsTotal<10){helpAffichMins="0";}

                    // Affichage du "timer"
                    timerValue.setText("" + helpAffichMins + minutesTempsTotal + ":"
                            + String.format("%02d", secondesTempstotal));

                }

                public void onFinish() {
                    updatedTime = 0;
                    // Affichage du "timer"
                    timerValue.setText("00:00");
                    timer = null;
                    updatedTime = valTimeToSave;
                    startButton.setText("Recommencer");
                }
            }.start();
        }
    }



    // Mise à jour graphique
    private void miseAJour() {
        int minutesTempsTotal;
        int secondesTempstotal;
        int secondesRestantes;

            // Décompositions en secondes et minutes
            secondesTempstotal = this.nbCycles*(this.secTempsEffort+this.secTempsPause);

            if(nouvelleActivite==true) {
                updatedTime = secondesTempstotal * 1000;
            }
            else if(nouvelleActivite!=true && updatedTime!=this.secTempsTotal * 1000)
            {
                secondesTempstotal = (int) (updatedTime / 1000);
            }


            //SI L'ACTIVITE a été relancée

        this.secTempsTotal=secondesTempstotal;

        minutesTempsTotal = secondesTempstotal / 60;
        secondesRestantes = secondesTempstotal % 60;


        String helpAffichMins="";
        if(minutesTempsTotal<10){helpAffichMins="0";}

        // Affichage du "timer"
        timerValue.setText("" + helpAffichMins + minutesTempsTotal + ":"
                + String.format("%02d", secondesRestantes));
        valeurTempsTotal.setText("" + helpAffichMins + minutesTempsTotal + ":"
                + String.format("%02d", secondesRestantes));
    }



    // Fonction de sauvegarde de données appelée lorsque l'activité a été arrêtée et relancée
    protected void onSaveInstanceState(Bundle saveInstanceState) {

        saveInstanceState.putInt("nbCycles", getNbCycles());
        saveInstanceState.putString("valeurCycles", valeurCycles.getText().toString());

        saveInstanceState.putInt("secTempsEffort", getSecTempsEffort());
        saveInstanceState.putString("valeurTempsEffort", valeurTempsEffort.getText().toString());

        saveInstanceState.putInt("secTempsPause", getSecTempsPause());
        saveInstanceState.putString("valeurTempsPause", valeurTempsPause.getText().toString());
        //"reprendre" ou "pause"
        saveInstanceState.putString("boutonPause", pauseButton.getText().toString());
        //"commencer" ou "stop"
        saveInstanceState.putString("boutonCommencer", startButton.getText().toString());

        saveInstanceState.putInt("secTempsTotal", getSecTempsTotal());
        saveInstanceState.putLong("updatedTime", getUpdatedTime());

        //Si le timer avait été lancé reprendre là où i ls'est arrété
        if(getUpdatedTime()!=getValTimeToSave())
        {
            saveInstanceState.putLong("valTimeToSave", getUpdatedTime());
        }
        else
        {
            saveInstanceState.putLong("valTimeToSave", getValTimeToSave());
        }

        super.onSaveInstanceState(saveInstanceState);
    }

}
