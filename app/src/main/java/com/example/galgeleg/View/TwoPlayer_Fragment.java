package com.example.galgeleg.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galgeleg.Model.Settings;
import com.example.galgeleg.Model.Spillogik.Galgelogik;
import com.example.galgeleg.Model.Spillogik.WrapperGalgelogik;
import com.example.galgeleg.R;
import com.example.galgeleg.View.Game.Fragment3_Game;


public class TwoPlayer_Fragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Galgelogik logik = WrapperGalgelogik.getInstance();
    RecyclerView recyclerView;
    Spinner spinner;
    Button further;
    private String choosenWord;
    Settings  settings = new Settings();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twoplayer_fragment_layout, container, false); //Why do we not attatch to root????

        settings.setTwoPlayerMode(true);
        settings.printState();


        further = view.findViewById(R.id.TwoPlayerContinue);
        further.setOnClickListener(this);

        /*
        Creating an adapter for new list. This list is costumdesigned, just for the fun of it but not so pretty;).
         */
        spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.twoplayer_fragment_spinner_item_layout, R.id.SpinnerOrd, logik.getMuligtOrd());
        adapter.setDropDownViewResource(R.layout.twoplayer_fragment_spinner_item_layout);
        spinner.setAdapter(adapter);


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        System.out.println(logik.getMuligtOrd().get(position));
        choosenWord = logik.getMuligtOrd().get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //To do notify that if no word is selected.
    }

    @Override
    public void onClick(View v) {
        Fragment endgame = new Fragment3_Game();

        Bundle args = new Bundle();
        args.putString("choosenWord",choosenWord);
        args.putString("Two Player", "Hello My best friend");
        endgame.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold,endgame)
                .commit();
    }
}
