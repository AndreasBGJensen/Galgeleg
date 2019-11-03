package com.example.galgeleg;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.galgeleg.HighScore.ListViewAdapter;
import com.example.galgeleg.HighScore.UserHighScoreDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment5_highscore extends AppCompatActivity {


    List<UserHighScoreDTO> testList = new ArrayList<>();


    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userInit();

        recyclerView = new RecyclerView(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListViewAdapter(testList));

        setContentView(recyclerView);
    }

private void userInit(){
    for(int i = 0; i<200;i++){
        UserHighScoreDTO a = new UserHighScoreDTO("User: "+i+"",""+i+1+"");

        testList.add(a);
    }

}

}
