package com.example.galgeleg.View;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.galgeleg.R;

public class Velkomst_Fragment extends Fragment implements Runnable {

    //Anvender en handler til at udføre en animation fra hovedtråden.
    Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Velkomst_frag", "fragmentet blev vist!");

        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.tombstone_courthouse_gallows);
        iv.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.egen_anim));

        // Hvis savedInstanceState ikke er null er fragmentet ved at blive genstartet
        if (savedInstanceState == null) {
            handler.postDelayed(this, 3000); // <1> Kør run() om 3 sekunder
        }

        return iv;
    }

    public void run() {
        if (getActivity()==null) return; // Hvis brugeren er hoppet ud af aktiviteten
        Fragment fragment = new Fragment1_Frontpage();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragmentindhold, fragment)  // tom container i layout
                .commit();
    }



}

