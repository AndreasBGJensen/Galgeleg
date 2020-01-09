package com.example.galgeleg.View.Game;

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

import com.example.galgeleg.Controle.TwoPlayer.Media;
import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.Model.Settings;
import com.example.galgeleg.Model.Spillogik.Galgelogik;
import com.example.galgeleg.Model.Spillogik.WrapperGalgelogik;
import com.example.galgeleg.R;
import com.example.galgeleg.View.EndGame_Fragment;

public class Fragment3_Game extends Fragment implements View.OnClickListener {
    private Galgelogik logik = WrapperGalgelogik.getInstance();
    private TextView lettersGuessed, gæt, helloUser;
    private EditText letterInput;
    private Button submit;
    private ImageView errorIm;
    private FrameLayout errorImage;
    Settings settings = new Settings();
    Media afspiller;


    private ImageControl imageControle = new ImageControl();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_game_layout,container,false); //Why do we not attatch to root????

        afspiller = Media.getInstance(getActivity());
        afspiller.stop();

        //This is set so that it will not save ole guessedletters due to static variable
        logik.nulstil();


        Database base = Database.getInstance(getContext());

        helloUser = view.findViewById(R.id.User);
        lettersGuessed = view.findViewById(R.id.guessedLetters);
        letterInput = view.findViewById(R.id.letterInput);
        errorImage = view.findViewById(R.id.ErrorImage);
        gæt = view.findViewById(R.id.ForkerteGæt);
        submit = view.findViewById(R.id.submit);
        errorIm = view.findViewById(R.id.errorIm);

        submit.setOnClickListener(this);

        helloUser.setText("Hello "+base.getCurrentUser()+"!");

        //For deabugging
        logik.logStatus();

        //Transfering data from former fragment
        //Used this way to demonstrate a use of bundle
        //TODO: Implement by observer or into setting class
        Bundle args = getArguments();
        if(args != null){
            if(settings.isTwoplayer()) {
                helloUser.setText(args.getString("Two Player"));
                System.out.println(args.getString("choosenWord"));
                logik.setOrdet(args.getString("choosenWord"));
                System.out.println(logik.getOrdet());
                logik.opdaterSynligtOrd();
            }
        }
        lettersGuessed.setText(logik.getSynligtOrd());
        return view;
    }
    /*

    Have no use at the moment
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
    */
    @Override
    public void onClick(View v) {
        logik.gætBogstav(letterInput.getText().toString());
        gæt.setText(logik.getBrugteBogstaver().toString());
        lettersGuessed.setText(logik.getSynligtOrd());
        letterInput.setText("");
        imageControle.map(logik.getAntalForkerteBogstaver(),errorIm,errorImage);


        if(logik.erSpilletTabt()||logik.erSpilletVundet() || logik.erSpilletSlut()){

            Fragment endgame = new EndGame_Fragment();

            //Create a bundle to passe information to the endGame:Fragment
            //TODO: Change passing information by bundle
            Bundle args = new Bundle();
            args.putString("Winner",Outcome()[0]);
            args.putString("ordetSomSkalGættes",Outcome()[1]);
            args.putString("Antalforsøg",Outcome()[2]);

            if(settings.isTwoplayer()){
                args.putString("Two Player", "Hello My best friend");
            }

            endgame.setArguments(args);
            logik.nulstil();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold,endgame)
                    .commit();
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












