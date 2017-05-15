package mi1.test_sugarorm;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import java.util.List;

import mi1.test_sugarorm.data.Personne;
import mi1.test_sugarorm.data.PersonneDAO;

public class PersonnesActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnes);

        // Récupérer la liste des personnes dans la base de données
        final List<Personne> personnes = PersonneDAO.selectAll();

        // Récupérer l'objet graphique de type ListView
        listView = (ListView) findViewById(R.id.listView);


        /////////////////
        // LIENS pour la ListView
        // https://developer.android.com/guide/topics/ui/layout/listview.html
        // https://openclassrooms.com/courses/creez-des-applications-pour-android/des-widgets-plus-avances-et-des-boites-de-dialogue

        // Solution simple
        // android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android
        //ArrayAdapter<Personne> adapter = new ArrayAdapter<Personne>(this, android.R.layout.simple_list_item_1, personnes);

        // Solution customisée
        ArrayAdapter<Personne> adapter = new ArrayAdapter<Personne>(this, R.layout.activity_personnes_row, personnes) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // Inflate LAYOUT
                if(convertView == null){
                    LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = (ViewGroup)inflater.inflate(R.layout.activity_personnes_row, null);
                }

                // VIEW
                TextView text1 = (TextView) convertView.findViewById(R.id.text1);
                TextView text2 = (TextView) convertView.findViewById(R.id.text2);

                // Charger la vue avec les données
                Personne personne = personnes.get(position);
                text1.setText(personne.getNom());
                text2.setText(personne.getPrenom());

                //
                return convertView;
            }
        };

        listView.setAdapter(adapter);
    }
}
