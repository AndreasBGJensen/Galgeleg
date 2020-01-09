package com.example.galgeleg.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.example.galgeleg.Controle.TwoPlayer.Media;
import com.example.galgeleg.Model.Spillogik.Galgelogik;
import com.example.galgeleg.Model.Spillogik.WrapperGalgelogik;
import com.example.galgeleg.R;

import java.lang.ref.WeakReference;




/*
I denne opgave har jeg forsøgt at implementere lidt forskellige løsninger.
Til at løse opgave har jeg gjort brug af følgende:
- AsyncTask: til at hente ord fra DR
- Thread til Medie afspilning
- RecyclerView til at lave en highScore Liste
- ShearedPreferences til at gemme data på telefonen - Create User.
- Costom liste i two player mode - her kan der vælges et ord fra listen.
- Afspille en lyd - når der vindes et spil
- Animation når et spil vindes

Til navigation er der taget udgangspunkt i at tilbageknappen anvendes og jeg er godt klar over at aktiviteter bliver fjernet når denne anvendes.
 */

public class MainActivity extends AppCompatActivity {
    Galgelogik logik = WrapperGalgelogik.getInstance();
    static Media afspiller;
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);

        afspiller = Media.initiate(getApplicationContext());
        afspiller.play();

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

        /*Indhenter ord fra dr i for at når dette fragment laves for at alle ord er tilstede når spillet går igang.
        Indhenter ordet her i for at vi er sikre på at ordene er tilgængelige, hvis at der skal spilles two player*/
        AsyncTask1 AsyncTask = new AsyncTask1(this,logik);
        AsyncTask.execute();

    }

    public void showToast(CharSequence text) {
        //Source for this code snippet: https://developer.android.com/guide/topics/ui/notifiers/toasts

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

    }


    @Override
    protected void onStop() {
        super.onStop();
        afspiller.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        afspiller.initiate(getApplicationContext());
        afspiller.play();
    }

    @Override
    protected void onStart() {
        super.onStart();
        afspiller.initiate(getApplicationContext());
        afspiller.play();
    }

    private static class AsyncTask1 extends AsyncTask<String,String,String> {

        private final WeakReference<MainActivity> activityRef;
        private final Galgelogik logik;
        CharSequence text = "Downloaded ord fra DR!";

        AsyncTask1(MainActivity activity, Galgelogik logic) {
            this.activityRef = new WeakReference<>(activity);
            this.logik = logic;
        }

        @Override
        protected String doInBackground(String...Strings) {
            try {
                logik.hentOrdFraDr();

            }catch (Exception e){
                System.out.println("Noget gik galt da jeg skulle hente ord fra DR");
                text = "Kunne ikke downloade ord fra DR!";
                e.printStackTrace();

            }
            return logik.getSynligtOrd();
        }

        @Override
        protected void onPostExecute(String s) {
            for(int i = 0; i<logik.getMuligtOrd().size();i++){
                System.out.println(logik.getMuligtOrd().get(i));
            }
            if (activityRef.get() != null) {
                activityRef.get().showToast(text);

            }
        }
    }
}



