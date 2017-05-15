package mi1.test_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // DATA
    private int valeur;

    // VIEW
    private TextView valeurView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INIT DATA
        valeur = 0;

        // VIEW
        valeurView =  (TextView)findViewById(R.id.valeur);

        // Mise à jour graphique
        miseAJour();
    }


    // Mise à jour de la fenêtre graphique
    private void miseAJour() {
        valeurView.setText(Integer.toString(valeur));
    }


    public void onChangeValeur(View view) {

        // Modification de la DATA
        if (view.getId() == R.id.moins) {
            valeur--;

        } else if (view.getId() == R.id.plus) {
            valeur++;
        }

        // Mise à jour graphique
        miseAJour();
    }
}
