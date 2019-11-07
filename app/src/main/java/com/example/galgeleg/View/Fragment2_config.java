package com.example.galgeleg.View;

import android.os.AsyncTask;
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
    Button continueButton;
    Button goBack;
    Button createUser;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_config_layout,container,false); //Why do we not attatch to root????
        Database base = Database.getInstance(getContext());
        base.removeCurrentUser();
        message = view.findViewById(R.id.message);
        submitUser = view.findViewById(R.id.submitUser);


        continueButton = view.findViewById(R.id.PressButton);
        continueButton.setOnClickListener(this);

        goBack = view.findViewById(R.id.toFront);
        goBack.setOnClickListener(this);

        createUser = view.findViewById(R.id.CreateUser);
        createUser.setOnClickListener(this);


        return view;
    }




    @Override
    public void onClick(View v) {
        Database base = Database.getInstance(getContext());

        String enteredUsername = submitUser.getText().toString();

        if(v == continueButton) {
            if (base.checkUser(enteredUsername)) {
                //Place the player that i currently playing
                base.setCurrentUser(enteredUsername);
                //Navigate to fragment...
                ((MainActivity) getActivity()).setViewPager(2);
            } else {
                message.setText("You have entered a wrong username");

            }
        }else if(v==goBack){
            ((MainActivity) getActivity()).setViewPager(0);
        }else if(v==createUser){
            ((MainActivity) getActivity()).setViewPager(3);
        }




        }




}

