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

public class Fragment_addUser extends Fragment implements View.OnClickListener {
Button submit;
EditText username;
    Button goBack;
SharedPreferences prefs;
Database database;
static int count=0;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_adduser,container,false); //Why do we not attatch to root????


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
            } else {
                username.setText("Username already Exist");
            }
        }

        if(v==goBack){
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold,new Fragment1_Frontpage())
                    .commit();
        }

    }
}



