package mi1.test_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Exemple2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemple2);
    }

    public void finishActivity(View view) {

        // Arret de l'activité (Elle sera retirée de la pile)
        setResult(RESULT_OK);
        finish();
    }
}
