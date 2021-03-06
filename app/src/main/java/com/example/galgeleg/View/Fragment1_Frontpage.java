package com.example.galgeleg.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.galgeleg.R;

public class Fragment1_Frontpage extends Fragment implements View.OnClickListener {

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

        return view;
    }





    @Override
    public void onClick(View v) {
        //Navigate to fragment...
        if(v==startSpil) {
            ((MainActivity) getActivity()).setViewPager(1);
        }
        if(v==highscore){
            Intent i = new Intent(getContext(), Activity_highscore.class);
            startActivity(i);
        }
        //Navigate to activity
        if(v==opretUser){
            ((MainActivity) getActivity()).setViewPager(3);
        }

    }
}
