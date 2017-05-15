package mi1.mi1_tp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.graphics.Color;

public class Exercice2Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //On charge le XML pour créer hierarchie des composants graphiques
        setContentView(R.layout.activity_exercice2);



    }

    @Override
    public void onClick(View v){
    //faire qqchose
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        TextView reponse = (TextView) findViewById(R.id.text3);
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.reponseA:
                if (checked)
                    reponse.setText("Je vous déclare sans portable !");
                    reponse.setTextColor(Color.parseColor("red"));
                    break;
            case R.id.reponseB:
                if (checked) {
                    reponse.setText("Au moins vous pourrez prendre le dernier modèle !");
                    reponse.setTextColor(Color.parseColor("red"));
                }
                    break;
            case R.id.reponseC:
                if (checked)
                    reponse.setText("Bravo, vous avez une vraie culture publicitaire et un humour développé. Votre appareil est sauvé !");
                    reponse.setTextColor(Color.parseColor("green"));
                    //reponse.setTextColor(getResources().getColor(R.color.BLUE));
                    // Bravo, vous avez une vraie culture publicitaire et un humour développé. Votre appareil est sauvé.
                    break;
        }
    }
}
