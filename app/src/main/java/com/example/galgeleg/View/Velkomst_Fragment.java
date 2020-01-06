package com.example.galgeleg.View;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.galgeleg.Model.Spillogik.Galgelogik;
import com.example.galgeleg.R;

public class Velkomst_Fragment extends Fragment implements Runnable {
    Galgelogik logik = new Galgelogik();
    //Anvender en handler til at udføre en animation fra hovedtråden.
    Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Velkomst_frag", "fragmentet blev vist!");

        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.tombstone_courthouse_gallows);
        iv.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.egen_anim));

        // Hvis savedInstanceState ikke er null er fragmentet ved at blive genstartet
        if (savedInstanceState == null) {
            handler.postDelayed(this, 6000); // <1> Kør run() om 3 sekunder
        }

        //Indhenter ord fra dr i for at når dette fragment laves for at alle ord er tilstede når spillet går igang.
        AsyncTask1 AsyncTask = new AsyncTask1();
        AsyncTask.execute();

        return iv;
    }

    public void run() {
        if (getActivity()==null) return; // Hvis brugeren er hoppet ud af aktiviteten
        Fragment fragment = new Frontpage_Fragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragmentindhold, fragment)  // tom container i layout
                .commit();
    }

    class AsyncTask1 extends AsyncTask<String,String,String> {
        CharSequence text = "Downloaded ord fra DR!";
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

            //Source for this code snippet: https://developer.android.com/guide/topics/ui/notifiers/toasts
            Context context = getActivity();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }

}

