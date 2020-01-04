package com.example.galgeleg.Utility;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.galgeleg.R;

public class Transaction_Fragments extends Fragment {


    /*
    Method to save some code when commit fragment
     */
    public void transaction(Fragment instance, Context context){

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold,instance)
                .addToBackStack(null)
                .commit();
    }
}
