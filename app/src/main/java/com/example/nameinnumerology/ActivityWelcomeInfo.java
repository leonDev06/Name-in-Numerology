package com.example.nameinnumerology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActivityWelcomeInfo extends AppCompatActivity {
    //WIdgets
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_info);

        //Link Widgets
        tabLayout = findViewById(R.id.welcomeTabLayout);
        viewPager = findViewById(R.id.welcomeViewPager);

        //Create the Adapter object
        AdapterActWelcomeVP adapterActWelcomeVP = new AdapterActWelcomeVP(this);
        viewPager.setAdapter(adapterActWelcomeVP);

        //Responsible for the TabLayout
        new TabLayoutMediator(tabLayout, viewPager,
            new TabLayoutMediator.TabConfigurationStrategy(){
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position){
                    tab.setText(""+(position+1));

                }
            }).attach();
        }
}