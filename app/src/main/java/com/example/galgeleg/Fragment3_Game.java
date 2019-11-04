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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.galgeleg.Database.Database;

public class Fragment3_Game extends Fragment implements View.OnClickListener {
    private Galgelogik logik = new Galgelogik();
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
        lettersGuessed.setText(logik.getSynligtOrd());

        letterInput = view.findViewById(R.id.letterInput);

        gæt = view.findViewById(R.id.ForkerteGæt);

        submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(this);

        errorImage = view.findViewById(R.id.ErrorImage);
        errorIm = view.findViewById(R.id.errorIm);

        return view;

    }

    @Override
    public void onClick(View v) {

        if(logik.erSpilletSlut()){

            Intent i = new Intent(getActivity(),EndGame.class);
            i.putExtra("Winner",logik.erSpilletVundet());
            i.putExtra("Looser",logik.erSpilletTabt());
            i.putExtra("ordetSomSkalGættes",logik.getOrdet());
            i.putExtra("Antalforsøg",logik.getBrugteBogstaver().size());
            startActivity(i);
            return;
        }


        logik.gætBogstav(letterInput.getText().toString());
        gæt.setText(logik.getBrugteBogstaver().toString());
        lettersGuessed.setText(logik.getSynligtOrd());
        letterInput.setText("");

        imageControle.map(logik.getAntalForkerteBogstaver(),errorIm,errorImage);

    }

}










