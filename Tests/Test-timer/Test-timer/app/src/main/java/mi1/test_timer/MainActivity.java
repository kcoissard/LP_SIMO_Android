package mi1.test_timer;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// EXEMPLE extrait de
// https://examples.javacodegeeks.com/android/core/os/handler/android-timer-example/

public class MainActivity extends AppCompatActivity {

    // VIEW
    private Button startButton;
    private Button pauseButton;
    private TextView timerValue;

    // DATA
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;

    // HANDLER
    private Handler customHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer les view
        timerValue = (TextView) findViewById(R.id.timerValue);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
    }

    // Créer un objet de type Runnable (thread) pour calculer le temps écoulé et lancer la mise à jour graphique
    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            // Calcul du temps écoulé
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            // Calcul du "timer" à afficher
            // Ajout du temps écoulé au timeSwapBuff
            updatedTime = timeSwapBuff + timeInMilliseconds;

            // Mise à jour graphique
            miseAJour();

            // Lancement de updateTimerThread
            customHandler.postDelayed(this, 0);
        }



    };


    // Mise à jour graphique
    private void miseAJour() {

        // Décompositions en secondes et minutes
        int secs = (int) (updatedTime / 1000);
        int mins = secs / 60;
        secs = secs % 60;
        int milliseconds = (int) (updatedTime % 1000);

        // Affichage du "timer"
        timerValue.setText("" + mins + ":"
                + String.format("%02d", secs) + ":"
                + String.format("%03d", milliseconds));

    }

    // Lancer le timer
    public void onStart(View view) {
        // Récupération de l'heure de départ
        startTime = SystemClock.uptimeMillis();

        // Lancement de updateTimerThread
        customHandler.postDelayed(updateTimerThread, 0);
    }

    // Arreter le timer
    public void onPause(View view) {
        // Enregistrement du timer à la pause
        timeSwapBuff += timeInMilliseconds;

        // Arrêt de updateTimerThread
        customHandler.removeCallbacks(updateTimerThread);
    }
}
