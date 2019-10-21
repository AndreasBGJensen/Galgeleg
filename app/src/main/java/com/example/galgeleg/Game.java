package com.example.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class  Game extends AppCompatActivity implements View.OnClickListener {
Galgelogik logik = new Galgelogik();
    TextView lettersGuessed, gæt;
    EditText letterInput;
    Button submit;
    FrameLayout errorImage;
    ImageView errorIm;
    ImageControl imageControle = new ImageControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);



        lettersGuessed = findViewById(R.id.guessedLetters);
        lettersGuessed.setText(logik.getSynligtOrd());

        letterInput = findViewById(R.id.letterInput);

        gæt = findViewById(R.id.ForkerteGæt);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);

        errorImage = findViewById(R.id.ErrorImage);
        errorIm = new ImageView(this);



    }

    @Override
    public void onClick(View v) {

        if(logik.erSpilletSlut()){

            Intent i = new Intent(this,EndGame.class);
            i.putExtra("Winner",logik.erSpilletVundet());
            i.putExtra("Looser",logik.erSpilletTabt());
            i.putExtra("ordetSomSkalGættes",logik.getOrdet());
            startActivity(i);
            return;
        }


        logik.gætBogstav(letterInput.getText().toString());
        gæt.setText(logik.getBrugteBogstaver().toString());
        lettersGuessed.setText(logik.getSynligtOrd());

        imageControle.map(logik.getAntalForkerteBogstaver(),errorIm,errorImage);

    }

}










