package com.example.galgeleg.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.galgeleg.Model.Spillogik.Galgelogik;
import com.example.galgeleg.R;


public class Fragment1_Frontpage extends Fragment implements View.OnClickListener {
Galgelogik logik = new Galgelogik();
    Button startSpil;
    Button highscore;
    Button opretUser;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_frontpage,container,false); //Why do we not attatch to root????


        startSpil = view.findViewById(R.id.PlayButton);
        startSpil.setOnClickListener(this);


        highscore = view.findViewById(R.id.highscore);
        highscore.setOnClickListener(this);

        opretUser = view.findViewById(R.id.opretUser);
        opretUser.setOnClickListener(this);


        //Indhenter ord fra dr i for at når dette fragment laves for at alle ord er tilstede når spillet går igang.
        AsyncTask1 AsyncTask = new AsyncTask1();
        AsyncTask.execute();

        return view;
    }





    @Override
    public void onClick(View v) {

        Fragment instance = new Fragment();
        if(v == startSpil){

                    instance = new Fragment2_config();
        }

        if(v == opretUser){
                    instance = new Fragment_addUser();
        }

        if(v == highscore){

            instance = new Fragment_Highscore();

        }

        transaction(instance);



    }

    /*
      Method to save some code when commit fragment
       */
    public void transaction(Fragment instance){

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold,instance)
                .addToBackStack(null)
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
