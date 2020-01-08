package com.example.galgeleg.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.R;
import com.example.galgeleg.View.Game.Fragment3_Game;

public class AddUser_Fragment extends Fragment implements View.OnClickListener {
Button submit;
EditText username;
    Button goBack;
SharedPreferences prefs;
Database database;
static int count=0;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.adduser_fragment_layout,container,false); //Why do we not attatch to root????


        submit = view.findViewById(R.id.submit);
        username = view.findViewById(R.id.username);

        goBack = view.findViewById(R.id.goBack);
        goBack.setOnClickListener(this);
        submit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v==submit) {
            database = Database.getInstance(getContext());
            String userName = username.getText().toString();


            boolean request = database.addUser(userName, "0");
            if (request) {
                username.setText("User added");

                database.setCurrentUser(userName);
                //Navigate to fragment...


                Fragment fragment = new Fragment3_Game();

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.fragmentindhold,fragment)

                        .commit();

            } else {
                username.setText("Not valid" );
            }
        }

        if(v==goBack){
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold,new Frontpage_Fragment())
                    .commit();
        }

    }




}



