package com.example.galgeleg.View.Game;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.Model.Spillogik.Galgelogik;
import com.example.galgeleg.R;
import com.example.galgeleg.View.Fragment_EndGame;

public class Fragment3_Game extends Fragment implements View.OnClickListener {
    private static Galgelogik logik = new Galgelogik();
    private TextView lettersGuessed, gæt, helloUser;
    private EditText letterInput;
    private Button submit;
    private ImageView errorIm;
    private FrameLayout errorImage;


    private ImageControl imageControle = new ImageControl();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_game_layout,container,false); //Why do we not attatch to root????


        Database base = Database.getInstance(getContext());
        helloUser = view.findViewById(R.id.User);
        helloUser.setText("Hello "+base.getCurrentUser()+"!");

        lettersGuessed = view.findViewById(R.id.guessedLetters);

        letterInput = view.findViewById(R.id.letterInput);

        gæt = view.findViewById(R.id.ForkerteGæt);

        submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(this);

        errorImage = view.findViewById(R.id.ErrorImage);
        errorIm = view.findViewById(R.id.errorIm);

        logik.logStatus();
        lettersGuessed.setText(logik.getSynligtOrd());



        return view;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("Fragment3_Game", "setUserVisibleHint: "+isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Database base = Database.getInstance(getContext());
            helloUser.setText("Hello "+base.getCurrentUser()+"!");
            lettersGuessed.setText(logik.getSynligtOrd());

        }
    }

    @Override
    public void onClick(View v) {




        logik.gætBogstav(letterInput.getText().toString());
        gæt.setText(logik.getBrugteBogstaver().toString());
        lettersGuessed.setText(logik.getSynligtOrd());
        letterInput.setText("");

        imageControle.map(logik.getAntalForkerteBogstaver(),errorIm,errorImage);


        if(logik.erSpilletTabt()||logik.erSpilletVundet() || logik.erSpilletSlut()){

            Fragment endgame = new Fragment_EndGame();

            Bundle args = new Bundle();
            args.putString("Winner",Outcome()[0]);
            args.putString("ordetSomSkalGættes",Outcome()[1]);
            args.putString("Antalforsøg",Outcome()[2]);
            endgame.setArguments(args);
            logik.nulstil();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold,endgame)
                    .addToBackStack(null)
                    .commit();


           /* Intent i = new Intent(getActivity(), Fragment_EndGame.class);
            i.putExtra("Winner",logik.erSpilletVundet());
            i.putExtra("Looser",logik.erSpilletTabt());
            i.putExtra("ordetSomSkalGættes",logik.getOrdet());
            i.putExtra("Antalforsøg",logik.getBrugteBogstaver().size());
            startActivity(i);*/


        }

    }


    private String[] Outcome(){
        String[] feedback = new String[3];

        if(logik.erSpilletVundet()){
            feedback[0]= "Winner";
        }else {
            feedback[0]="Looser";
        }
        //Ordet som skulle gættes
        feedback[1] = logik.getOrdet();

        feedback[2] = Integer.toString(logik.getBrugteBogstaver().size());

        return feedback;
    }


}












