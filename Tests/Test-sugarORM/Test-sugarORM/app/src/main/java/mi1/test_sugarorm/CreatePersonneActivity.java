package mi1.test_sugarorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import mi1.test_sugarorm.data.Personne;

public class CreatePersonneActivity extends AppCompatActivity {


    // View
    private EditText nomView;
    private EditText prenomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personne);

        // Récupération des editText
        nomView = (EditText) findViewById(R.id.nom);
        prenomView = (EditText) findViewById(R.id.prenom);
    }


    public void creerPersonne(View view) {

        String nom = nomView.getText().toString();
        String prenom = prenomView.getText().toString();

        //
        Personne personne = new Personne(nom, prenom);
        personne.save();

        //
        finish();
    }
}
