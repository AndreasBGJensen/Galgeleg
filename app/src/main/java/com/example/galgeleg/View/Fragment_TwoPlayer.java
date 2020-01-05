package com.example.galgeleg.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galgeleg.Controle.TwoPlayer.MyWordAdapter;
import com.example.galgeleg.Model.Spillogik.Galgelogik;
import com.example.galgeleg.R;



public class Fragment_TwoPlayer extends Fragment {
    Galgelogik logik = new Galgelogik();
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twoplayer, container, false); //Why do we not attatch to root????

        recyclerView = view.findViewById(R.id.WordsRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyWordAdapter(logik.getMuligtOrd()));

        return view;
    }
}
