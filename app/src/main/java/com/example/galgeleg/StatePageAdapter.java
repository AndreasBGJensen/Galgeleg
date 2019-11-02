package com.example.galgeleg;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StatePageAdapter extends FragmentStatePagerAdapter {

   private final List<Fragment> myFragments = new ArrayList<>();
   private final List<Fragment> getMyFragmentsTitle = new ArrayList<>(); //If I want to keep track of my fragments.


    public StatePageAdapter(FragmentManager fm) {
        super(fm);
    }

    /*
    Returning the requested fragment
     */
    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    /*
    Returning the number of fragments
     */
    @Override
    public int getCount() {
        return myFragments.size();
    }

    private void addFragment(Fragment frag){
        myFragments.add(frag);
    }
}
