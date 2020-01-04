package com.example.galgeleg.Model.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galgeleg.Model.Spillogik.HighScore.UserHighScoreDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
Denne klasse er lavet til en singleton. Dette skyldes at jeg har
forsøgt mig med at overføre data ved at opbevare den i SheredPræferences.
Dette medførger dog at der skal oprettes et Database instans i flere fragmenter og Aktiviteter.

Årsagen til at jeg har valgt at overfre data på denne måde er hovedsageligt at jeg syntes at det kunne være sjovt at prøve,
få mere kendskab til sharedpræferences og fordi at jeg gerne ville prøve at lave min egen StagePageAdapter.
 */

public class Database extends AppCompatActivity {
    static Database base = null;


     SharedPreferences pref;
     List<UserHighScoreDTO> testList = new ArrayList<>();



    private Database(Context context){
         pref = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public static Database getInstance(Context context){
        if(base == null){
            base = new Database(context);
        }

        return base;
    }



/*
Metoderne nedenfor tilgår SharedPreferences, hvor der laves CRUD operatoner på dataen.
 */


    public boolean checkUser(String username){
        clean();
        if(pref.getAll().containsKey(username) || username.equals("User added") || username.equals("Username")){
            return true;
        }
        return false;
    }

    public boolean addUser(String username, String score){
        if(checkUser(username)){
            return false;
        }else {
            pref.edit().putString(username, score).apply();
            System.out.println("User added to database");
            return true;
        }
    }

    public String getUser(String username){
        return pref.getString(username, "(ukendt)");
    }

    public List<UserHighScoreDTO> getUserScore(){
        testList.clear();
        Map<String,?> allUsers = pref.getAll();


            for (Map.Entry<String, ?> entry : allUsers.entrySet()) {

                if(entry.getValue().toString().matches("\\d+")) { //Checking if the user list only contains username and a score

                        UserHighScoreDTO user = new UserHighScoreDTO(entry.getKey(), entry.getValue().toString());
                        testList.add(user);

                }
            }

        return testList;
    }


    public void updateUser(String username, String score){
        String newUser = pref.getString(username,"(ukendt)");
        pref.edit().remove(username).apply();
        addUser(username,score);
    }

    /*
    Denne metode anvendes til at state, hvilken bruger som er "logget ind".
     */

    public void setCurrentUser(String username){
        pref.edit().putString("currentUser",username).apply();
    }

    public String getCurrentUser(){
        return pref.getString("currentUser","(ukendt)");
    }

    public void removeCurrentUser(){
        pref.edit().remove("currentUser");
    }

    /*
    Deleting unwanted users, Denne metode blev er blevet oprettet efter at det er blevet sikret at der ikke kan oprettes en bruger med navnene "User name" eller Username.
     */

    private void clean(){


        Map<String,?> allUsers = pref.getAll();


        for (Map.Entry<String, ?> entry : allUsers.entrySet()) {

            if(entry.getValue().toString().matches("\\d+")) {//Checking if the user list only contains username and a score
                String key  = entry.getKey();
                if(entry.getKey().equals("Username") || entry.getKey().equals("User added")){
                    pref.edit().remove(key).apply();
                }



            }
        }

    }

}
