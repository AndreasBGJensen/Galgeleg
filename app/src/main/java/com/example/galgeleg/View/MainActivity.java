package com.example.galgeleg.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.Window;
import com.example.galgeleg.R;




/*
I denne opgave har jeg forsøgt at implementere lidt forskellige løsninger.
Til at løse opgave har jeg gjort brug af følgende:
- AsyncTask: til at hente ord fra DR
- RecyclerView til at lave en highScore Liste
- ShearedPreferences til at gemme data på telefonen - Create User.
- Costom liste i two player mode - her kan der vælges et ord fra listen.
- Afspille en lyd - når der vindes et spil
 */

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hovedskaerm_fragmentindehold);

        if (savedInstanceState == null) {
            Fragment fragment = new Velkomst_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentindhold, fragment)  // tom container i layout
                    .commit();
        }

        setTitle("Hovedaktivitet");
        // Man kan trykke på app-ikonet i øverste venstre hjørne
        // (og det betyder at brugeren vil navigere op i hierakiet)
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
