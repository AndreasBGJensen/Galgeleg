package com.example.galgeleg.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.R;

public class Fragment2_config extends Fragment implements View.OnClickListener {

    TextView message;
    EditText submitUser;
    Button pressScreen;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_config_layout,container,false); //Why do we not attatch to root????

        message = view.findViewById(R.id.message);
        submitUser = view.findViewById(R.id.submitUser);


        pressScreen = view.findViewById(R.id.PressButton);
        pressScreen.setOnClickListener(this);

        return view;
    }




    @Override
    public void onClick(View v) {
        Database base = Database.getInstance(getContext());

        String enteredUsername = submitUser.getText().toString();


        if(base.checkUser(enteredUsername)){
            //Place the player that i currently playing
            base.setCurrentUser(enteredUsername);
            //Navigate to fragment...
            ((MainActivity)getActivity()).setViewPager(2);
        }else{
            message.setText("You have entered a wrong username");
        }




        }

}

