package com.example.galgeleg.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.Model.Spillogik.HighScore.CalculatScore;
import com.example.galgeleg.R;

public class Activity_EndGame extends AppCompatActivity implements View.OnClickListener {
    String ordet;
    String udfald;
    Intent i;
    Button tilHighscore;
    Button frontPage;
    CalculatScore scoreCalculation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_layout);
        i =getIntent();

        TextView endGameMsg = findViewById(R.id.msg);
        endGameMsg.setText(howDidItTurnOut(i));

        tilHighscore = findViewById(R.id.highScore);
        tilHighscore.setOnClickListener(this);

        frontPage = findViewById(R.id.FrontPage);
        frontPage.setOnClickListener(this);



    }



    @Override
    public void onClick(View v) {
        Database basen = Database.getInstance(getApplicationContext());
    if(v== tilHighscore) {

        Intent i = new Intent(this, Activity_highscore.class );
        startActivity(i);

    }
    if(v==frontPage){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        basen.removeCurrentUser();
    }
    }


    private String howDidItTurnOut(Intent i){
        Database basen = Database.getInstance(getApplicationContext());
        Boolean winner = i.getBooleanExtra("Winner", false);
        Boolean looser = i.getBooleanExtra("Looser", false);
        ordet = i.getStringExtra("ordetSomSkalGættes");

            if(looser){

                udfald = "Du har desværre tabt spillet\n Ordet du skulle gætte var " + ordet;

            }else if(winner){

                udfald = "Tillykke du gætte ordet på "+i.getStringExtra("Antalforsøg")+" forsøg\n Ordet var:\n "+ordet;
                String user = basen.getCurrentUser();
                String newScorePoints = i.getStringExtra("Antalforsøg");
                scoreCalculation = new CalculatScore(basen.getUser(user),newScorePoints);

                basen.updateUser(basen.getCurrentUser(),scoreCalculation.getNewScore());

            }

            return udfald;
        }



}

