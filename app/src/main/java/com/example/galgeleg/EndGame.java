package com.example.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galgeleg.Database.Database;
import com.example.galgeleg.HighScore.CalculatScore;

public class  EndGame extends AppCompatActivity implements View.OnClickListener {
    String ordet;
String udfald;
Intent i;
Button spilIgen;
Button frontPage;
CalculatScore scoreCalculation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_layout);
        i =getIntent();

        TextView endGameMsg = findViewById(R.id.msg);
        endGameMsg.setText(howDidItTurnOut(i));

        spilIgen = findViewById(R.id.Spiligen);
        spilIgen.setOnClickListener(this);

        frontPage = findViewById(R.id.FrontPage);
        frontPage.setOnClickListener(this);



    }



    @Override
    public void onClick(View v) {
        Database basen = Database.getInstance(getApplicationContext());
    if(v==spilIgen) {
        Intent i = new Intent(this, Fragment3_Game.class);
        startActivity(i);
        Intent a = new Intent(getApplicationContext(), Fragment3_Game.class);
        startActivity(a);
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

                udfald = "Tillykke du gætte ordet på "+i.getStringExtra("Antalforsøg")+"\n Ordet var:\n "+ordet;
                String user = basen.getCurrentUser();
                String newScorePoints = i.getStringExtra("Antalforsøg");
                scoreCalculation = new CalculatScore(basen.getUser(user),newScorePoints);

                basen.updateUser(basen.getCurrentUser(),scoreCalculation.getNewScore());

            }

            return udfald;
        }

}

