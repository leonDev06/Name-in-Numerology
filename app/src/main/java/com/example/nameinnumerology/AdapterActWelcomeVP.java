package com.example.nameinnumerology;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

//The Adapter for the ViewPager Fragments in ActivityWelcomeInfo
public class AdapterActWelcomeVP extends FragmentStateAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public AdapterActWelcomeVP(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //Add the fragments to the arrayList
        fragments.add(new FragmentWelcomeFirst());
        fragments.add(new FragmentWelcomeSecond());
        fragments.add(new FragmentWelcomeThird());

        //return the fragment based on the position
        return fragments.get(position);
    }

    //Specicifies fixed length of tabs
    @Override
    public int getItemCount() {
        return 3;
    }
}
