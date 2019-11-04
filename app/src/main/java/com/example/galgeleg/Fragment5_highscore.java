package com.example.galgeleg;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galgeleg.Database.Database;
import com.example.galgeleg.HighScore.ListViewAdapter;
import com.example.galgeleg.HighScore.UserHighScoreDTO;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Fragment5_highscore extends AppCompatActivity{





    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Here i initializing alot of test users.
        Database database = Database.getInstance(getBaseContext());

        List<UserHighScoreDTO> testList =database.getUserScore();
        Collections.sort(testList);

        System.out.println(testList);

        recyclerView = new RecyclerView(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListViewAdapter(testList));

        setContentView(recyclerView);
    }



}
