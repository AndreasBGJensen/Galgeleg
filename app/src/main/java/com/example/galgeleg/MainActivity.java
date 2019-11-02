package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

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

        //Her we insert the first fragment
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
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int i){
        myViewPager.setCurrentItem(i);
    }

}
