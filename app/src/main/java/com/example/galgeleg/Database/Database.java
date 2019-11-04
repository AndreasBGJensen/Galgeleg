package com.example.galgeleg.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.galgeleg.HighScore.UserHighScoreDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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






    public boolean checkUser(String username){
        if(pref.getAll().containsKey(username)){
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

    public void setCurrentUser(String username){
        pref.edit().putString("currentUser",username).apply();
    }

    public String getCurrentUser(){
        return pref.getString("currentUser","(ukendt)");
    }

    public void removeCurrentUser(){
        pref.edit().remove("currentUser");
    }



}
