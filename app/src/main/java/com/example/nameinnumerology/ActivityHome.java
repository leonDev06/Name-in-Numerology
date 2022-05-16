package com.example.nameinnumerology;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class ActivityHome extends AppCompatActivity {
    //Widgets
    private Button mButtonMusic;

    //Defined classes
    private SoundManager soundManager;
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Link Widgets
        mButtonMusic = findViewById(R.id.actHomeButtonMusic);

        //Initialize defined/helper objects
        soundManager = new SoundManager(this);
        fragmentManager = getSupportFragmentManager();
        //Play BGM
        soundManager.playBgm(R.raw.bgm);
        //Load-up initial fragment
        loadFirstFragment(R.id.actHomeMainFragHolder, new FragmentNumerologyEnterName());

        //Clickable Buttons
        mButtonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!soundManager.isBgmMuted()){
                    mButtonMusic.setBackgroundResource(R.drawable.ic_baseline_music_off_24);
                    soundManager.muteBgm();
                }else{
                    mButtonMusic.setBackgroundResource(R.drawable.ic_baseline_music_note_24);
                    soundManager.unMuteBgm();
                }
            }
        });

    }
    //Static method used to handle all fragment replacements in the home activity
    public static void replaceFragment(int layout, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //Used to load the first fragment. Doesn't add to backstack
    private void loadFirstFragment(int layout, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }

    //Override activity lifecycle methods
    @Override
    protected void onPause(){
        super.onPause();
        if(soundManager.getMediaPLayer()!=null){
            soundManager.getMediaPLayer().pause();
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        soundManager.getMediaPLayer().start();
    }
}