package com.example.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2_config extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_config_layout,container,false); //Why do we not attatch to root????

        Button pressScreen = view.findViewById(R.id.PressScreen);
        pressScreen.setOnClickListener(this);

        return view;
    }




    @Override
    public void onClick(View v) {
            //Navigate to fragment...
            ((MainActivity)getActivity()).setViewPager(2);
        }

}

