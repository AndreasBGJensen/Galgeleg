package com.example.galgeleg.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.Model.Spillogik.HighScore.ListViewAdapter;
import com.example.galgeleg.Model.Spillogik.HighScore.UserHighScoreDTO;
import com.example.galgeleg.R;

import java.util.Collections;
import java.util.List;

public class Fragment_Highscore extends Fragment {


    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.highscore_listitem,container,false); //Why do we not attatch to root????
        //Here i initializing alot of test users.
        Database database = Database.getInstance(getActivity().getApplicationContext());

        List<UserHighScoreDTO> testList =database.getUserScore();
        Collections.sort(testList);

        System.out.println(testList);

        recyclerView = new RecyclerView(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ListViewAdapter(testList));

        return view;
    }
}
