package com.example.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class config extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_layout);

        Button pressScreen = findViewById(R.id.PressScreen);
        pressScreen.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,Game.class);
        startActivity(i);

    }
}
