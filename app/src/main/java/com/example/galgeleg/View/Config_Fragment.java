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
import com.example.galgeleg.View.Game.Fragment3_Game;

public class Config_Fragment extends Fragment implements View.OnClickListener {

    TextView message;
    EditText submitUser;
    Button continueButton;
    Button goBack;
    Button createUser;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.config_fragment_layout,container,false); //Why do we not attatch to root????
        Database base = Database.getInstance(getContext());
        base.removeCurrentUser();
        message = view.findViewById(R.id.message);
        submitUser = view.findViewById(R.id.submitUser);


        continueButton = view.findViewById(R.id.config_continue);
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
            submitUser.setText("");
            if (base.checkUser(enteredUsername)) {
                //Place the player that i currently playing
                base.setCurrentUser(enteredUsername);
                //Navigate to fragment...

                Fragment fragment = new Fragment3_Game();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.fragmentindhold,fragment)
                        .addToBackStack(null) //Addes til backstacken: UseCase: Hvis bruger gerne vil spille med et andet navn
                        .commit();
            } else {
                message.setText("You have entered a wrong username");

            }
        }else if(v==goBack){
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold,new Frontpage_Fragment())
                    .addToBackStack(null)
                    .commit();
        }else if(v==createUser){
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold,new AddUser_Fragment())
                    .addToBackStack(null)
                    .commit();
        }




        }




}

