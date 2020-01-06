package com.example.galgeleg.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.galgeleg.R;


public class Frontpage_Fragment extends Fragment implements View.OnClickListener {

    Button startSpil;
    Button highscore;
    Button opretUser;
    Button twoPlayer;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_frontpage,container,false); //Why do we not attatch to root????


        startSpil = view.findViewById(R.id.PlayButton);
        startSpil.setOnClickListener(this);


        highscore = view.findViewById(R.id.highscore);
        highscore.setOnClickListener(this);

        opretUser = view.findViewById(R.id.opretUser);
        opretUser.setOnClickListener(this);

        twoPlayer = view.findViewById(R.id.TwoPlayer);
        twoPlayer.setOnClickListener(this);




        return view;
    }





    @Override
    public void onClick(View v) {

        Fragment instance = new Fragment();
        if(v == startSpil){

                    instance = new Config_Fragment();
        }

        if(v == opretUser){
                    instance = new AddUser_Fragment();
        }

        if(v == highscore){

            instance = new Highscore_Fragment();

        }

        if(v == twoPlayer){
            instance = new TwoPlayer_Fragment();
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




}
