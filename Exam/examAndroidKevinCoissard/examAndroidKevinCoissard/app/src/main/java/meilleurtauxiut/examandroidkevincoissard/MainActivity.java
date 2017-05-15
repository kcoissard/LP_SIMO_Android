package meilleurtauxiut.examandroidkevincoissard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {

    Button buttonMontant1;
    String valeurMontant1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();


    }

    public void addListenerOnButton() {

        buttonMontant1 = (Button) findViewById(R.id.boutonCalcul);
        buttonMontant1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                EditText editText = (EditText)findViewById(R.id.montantPret);
                String valeurMontant1 = editText.getText().toString();

                Intent myIntent = new Intent(view.getContext(),MensualityActivity.class);
                myIntent.putExtra("mytext",valeurMontant1);
                startActivity(myIntent);

            }
        });
    }
}

