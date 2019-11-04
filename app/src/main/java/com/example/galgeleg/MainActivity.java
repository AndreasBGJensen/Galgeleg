package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.example.galgeleg.Database.Database;


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
        //adapter.addFragment(new Fragment5_highscore()); //The highScore list is made as an Activity instead.
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int i){
        myViewPager.setCurrentItem(i);
    }


}
