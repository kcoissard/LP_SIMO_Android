package mi1.test_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE = "Bonjour !";
    private static final int REQUEST_CODE_EXERCICE_2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startIntent(View view) {

        // Créer l'intent
        Intent intent = new Intent(this, Exemple1Activity.class);

        // Passer un extra en paramètre de l'intent
        intent.putExtra(Exemple1Activity.EXTRA_MESSAGE, MESSAGE);

        // Lancer l'intent
        startActivity(intent);

    }

    public void startIntentWithResult(View view) {

        // Créer l'intent
        Intent intent = new Intent(this, Exemple2Activity.class);

        // Lancer l'intent
        startActivityForResult(intent, REQUEST_CODE_EXERCICE_2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EXERCICE_2) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Retour exercice 2 avec RESULT_OK", Toast.LENGTH_LONG).show();

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Retour exercice 2 avec RESULT_CANCELED", Toast.LENGTH_LONG).show();
            }
        }
    }
}
