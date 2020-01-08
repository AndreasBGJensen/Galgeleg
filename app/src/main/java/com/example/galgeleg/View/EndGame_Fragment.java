package com.example.galgeleg.View;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.galgeleg.Controle.TwoPlayer.Media;
import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.Model.Settings;
import com.example.galgeleg.Model.Spillogik.HighScore.CalculatScore;
import com.example.galgeleg.R;
import com.example.galgeleg.View.Game.Fragment3_Game;
import com.github.jinatonic.confetti.CommonConfetti;
//import com.example.galgeleg.Utility.Transaction_Fragments;

public class EndGame_Fragment extends Fragment implements View.OnClickListener {
    String ordet;
    String udfald;
    Button tilHighscore;
    Button spilIgen;
    Button frontPage;
    CalculatScore scoreCalculation;
    Settings settings = new Settings();
    Boolean twoPlayerMode = false;
    Media afspiller;


    //Transaction_Fragments utilToFragment = new Transaction_Fragments();



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.endgame_fragment_layout,container,false);

        Bundle args = getArguments();

        afspiller = new Media(getActivity());

        //Adding the confetti animation
        if(args.getString("Winner").equals("Winner")) {
            CommonConfetti.rainingConfetti(container, new int[]{Color.BLUE})
                    .oneShot(); //Only one shot of confetti will be rendered
        }

        if(settings.isTwoplayer()){

            twoPlayerMode = true;
        }


            spilIgen = view.findViewById(R.id.SpilIgen);
            spilIgen.setOnClickListener(this);



        TextView endGameMsg = view.findViewById(R.id.msg);
        //endGameMsg.setText(howDidItTurnOut(i));
        endGameMsg.setText(howDidItTurnOut(args));
        tilHighscore = view.findViewById(R.id.highScore);
        tilHighscore.setOnClickListener(this);

        frontPage = view.findViewById(R.id.FrontPage);
        frontPage.setOnClickListener(this);



        return view;

    }



    @Override
    public void onClick(View v) {

        Fragment fragment = null;
        Database basen = Database.getInstance(getActivity().getApplicationContext());
    if(v== tilHighscore) {

        fragment = new Highscore_Fragment();
        getFragmentManager().beginTransaction()
                .add(R.id.fragmentindhold,fragment)
                .addToBackStack(null)
                .commit();


    }
    else if(v==frontPage){

        fragment = new Frontpage_Fragment();

        basen.removeCurrentUser();
        transaction(fragment);
    } else if(v==spilIgen) {

        if (twoPlayerMode) {
            fragment = new TwoPlayer_Fragment();
            transaction(fragment);

        } else {
            fragment = new Fragment3_Game();
            transaction(fragment);
        }
    }

    }

    /*
      Method to save some code when commit fragment
       */
    public void transaction(Fragment instance){

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold,instance)
                .commit();
    }


    private String howDidItTurnOut(Bundle args){
        Database basen = Database.getInstance(getActivity().getApplicationContext());
        Boolean winner = false;
        Boolean looser = false;

        ordet = args.getString("ordetSomSkalGættes");


        if (args.getString("Winner").equals("Winner")) {
            winner = true;
        } else if (args.getString("Winner").equals("Looser")) {
            looser = true;
        }


        if(looser){

            udfald = "Du har desværre tabt spillet\n Ordet du skulle gætte var " + ordet;

        }else if(winner){

            afspiller.play();

            udfald = "Tillykke du gætte ordet på "+args.getString("Antalforsøg")+" forsøg\n Ordet var:\n "+ordet;
            String newScorePoints = args.getString("Antalforsøg");
            String user = basen.getCurrentUser();

            /*Hvis der ikke spilles two player skal der ikke lægges to point til. Dette bliver kontrolleret ved at hente et objekt fra ShearedPreferences
            Som kun bliver lavet, hvis at der spilles two player
             */

            if (!settings.isTwoplayer()) {
                scoreCalculation = new CalculatScore(basen.getUser(user), newScorePoints, ordet.length());

            basen.updateUser(basen.getCurrentUser(),scoreCalculation.getNewScore());

            } else {
                basen.setCurrentUser("My best friend");
            }

        }

        return udfald;
    }

    /*
        @Override
        public void onDestroyView() {
            afspiller.stop();
            super.onDestroyView();
        }
    */

}

