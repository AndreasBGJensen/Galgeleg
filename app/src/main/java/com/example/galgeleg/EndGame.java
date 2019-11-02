package com.example.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class  EndGame extends AppCompatActivity implements View.OnClickListener {
    String ordet;
String udfald;
Intent i;
Button spilIgen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_layout);
        i =getIntent();

        TextView endGameMsg = findViewById(R.id.msg);
        endGameMsg.setText(howDidItTurnOut(i));

        spilIgen = findViewById(R.id.Spiligen);
        spilIgen.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Fragment3_Game.class);
        startActivity(i);
    }


    private String howDidItTurnOut(Intent i){
    Boolean winner = i.getBooleanExtra("Winner", false);
        Boolean looser = i.getBooleanExtra("Looser", false);
        ordet = i.getStringExtra("ordetSomSkalGættes");

            if(looser){

                udfald = "Du har desværre tabt spillet\n Ordet du skulle gætte var" + ordet;

            }else if(winner){

                udfald = "Tillykke du gætte ordet\n Ordet var: "+ordet;
            }

            return udfald;
        }

}

