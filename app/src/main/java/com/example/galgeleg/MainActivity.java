package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frontpage);

        Button startSpil = findViewById(R.id.PlayButton);
        startSpil.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,config.class);
        startActivity(i);
    }
}
