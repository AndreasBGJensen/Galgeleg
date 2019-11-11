package com.example.galgeleg.View;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galgeleg.Model.Spillogik.HighScore.UserHighScoreDTO;

public class Activity_AddUser extends AppCompatActivity {



    public Activity_AddUser(UserHighScoreDTO user){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String fornavn = "Søren";
        prefs.edit().putString("fornavn", fornavn).apply();
        String Nytfornavn = prefs.getString("fornavn", "(ukendt)");

        System.out.println(Nytfornavn);


    }

}
