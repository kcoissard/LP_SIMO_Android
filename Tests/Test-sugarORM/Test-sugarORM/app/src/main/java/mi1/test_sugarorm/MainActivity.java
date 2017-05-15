package mi1.test_sugarorm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPersonnes(View view) {

        Intent intent = new Intent(this, PersonnesActivity.class);
        startActivity(intent);

    }


    public void createPersonne(View view) {

        Intent intent = new Intent(this, CreatePersonneActivity.class);
        startActivity(intent);
    }
}
