package com.example.galgeleg.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.galgeleg.Model.Database.Database;
import com.example.galgeleg.R;
import com.example.galgeleg.Controle.StatePageAdapter;
import com.example.galgeleg.View.Game.Fragment3_Game;


public class MainActivity extends AppCompatActivity {

    StatePageAdapter myAdapter;
    ViewPager myViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);


        /**
         * Return the FragmentManager for interacting with fragments associated
         * with this activity.
         */
        myAdapter = new StatePageAdapter(getSupportFragmentManager());


        myViewPager = (ViewPager) findViewById(R.id.container);


        //Setting up the viewpager
        addFragmentsToViewPager(myViewPager);

        //Here we insert the first fragment
        setViewPager(0);


    }

    /*
    When we enter the app and Activity_Main is launched we want to add all out fragments.
    I have made a method for this job.
     */
    private void addFragmentsToViewPager(ViewPager viewPager) {
        StatePageAdapter adapter = new StatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1_Frontpage());
        adapter.addFragment(new Fragment2_config());
        adapter.addFragment(new Fragment3_Game());
        adapter.addFragment(new Fragment_addUser());
        //adapter.addFragment(new Activity_highscore()); //The highScore list is made as an Activity instead.
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int i){
        myViewPager.setCurrentItem(i);
    }


}
