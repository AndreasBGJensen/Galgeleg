package com.example.galgeleg.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class Highscore_Fragment extends Fragment implements View.OnTouchListener {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.highscore_fragment_liste,container,false);

        //Here i initializing alot of test users.
        Database database = Database.getInstance(getContext());

        List<UserHighScoreDTO> testList =database.getUserScore();
        Collections.sort(testList);

        System.out.println(testList);

        recyclerView = view.findViewById(R.id.listRecycleView);
        recyclerView.setOnTouchListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ListViewAdapter(testList));

        return view;
    }


//Tab to remove highscore Liste
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        getFragmentManager().beginTransaction()
                //.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                .remove(this)
                .commit();
        return true;
    }
}




