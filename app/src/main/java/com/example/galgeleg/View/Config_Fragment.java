package com.example.galgeleg.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.R;
import com.example.galgeleg.View.Game.Fragment3_Game;

public class Config_Fragment extends Fragment implements View.OnClickListener {

    TextView message;
    EditText submitUser;
    Button continueButton;
    Button goBack;
    Button createUser;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.config_fragment_layout,container,false); //Why do we not attatch to root????
        Database base = Database.getInstance(getContext());
        base.removeCurrentUser();
        message = view.findViewById(R.id.message);
        submitUser = view.findViewById(R.id.submitUser);


        continueButton = view.findViewById(R.id.config_continue);
        continueButton.setOnClickListener(this);

        goBack = view.findViewById(R.id.toFront);
        goBack.setOnClickListener(this);

        createUser = view.findViewById(R.id.CreateUser);
        createUser.setOnClickListener(this);


        return view;
    }




    @Override
    public void onClick(View v) {
        Database base = Database.getInstance(getContext());

        String enteredUsername = submitUser.getText().toString();

        if(v == continueButton) {
            submitUser.setText("");
            if (base.checkUser(enteredUsername)) {
                //Place the player that i currently playing
                base.setCurrentUser(enteredUsername);
                //Navigate to fragment...



                /*
                Creating a Bundle and passing it to the Fragment Fragment3_Game
                This could have be done by just loading the usename from the PreferenceManager
                But i choose to do do it like this.
                 */
                Fragment fragment = new Fragment3_Game();


                //Delete this this is not used
               /* Bundle args = new Bundle();
                args.putString("UserPlaying",enteredUsername);
                fragment.setArguments(args);*
*/
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold,fragment)

                        .commit();
            } else {
                message.setText("You have entered a wrong username");

            }
        }else if(v==goBack){
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold,new Frontpage_Fragment())
                    .addToBackStack(null)
                    .commit();
        }else if(v==createUser){
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold,new AddUser_Fragment())
                    .addToBackStack(null)
                    .commit();
        }




        }




}

