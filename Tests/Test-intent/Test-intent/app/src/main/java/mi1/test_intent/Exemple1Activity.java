package mi1.test_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Exemple1Activity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "exemple1_message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemple1);

        // Récupérer l'extra en paramètre de l'intent
        String message = getIntent().getStringExtra(EXTRA_MESSAGE);

        //
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
