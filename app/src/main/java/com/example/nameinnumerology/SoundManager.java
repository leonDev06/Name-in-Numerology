package com.example.nameinnumerology;

import android.app.Activity;
import android.media.MediaPlayer;

public class SoundManager {
    //Member Variables
    private MediaPlayer mediaPLayer;
    private Activity activity;
    private boolean isBgmMuted;

    //Constructor
    public SoundManager(Activity activity){
        this.activity = activity;
    }
    //Getters
    public MediaPlayer getMediaPLayer() {
        return mediaPLayer;
    }
    public boolean isBgmMuted() {
        return isBgmMuted;
    }

    //Play the BGM
    public void playBgm(int audio){
        mediaPLayer = MediaPlayer.create(activity, audio);
        mediaPLayer.setLooping(true);
        mediaPLayer.setVolume(0.5f, 0.5f);
        isBgmMuted=false;
        mediaPLayer.start();
    }
    //Mutes BGM
    public void muteBgm(){
        mediaPLayer.setVolume(0,0);
        isBgmMuted = true;
    }
    //Unmute BGM
    public void unMuteBgm(){
        mediaPLayer.setVolume(0.5f, 0.5f);
        isBgmMuted=false;
    }
}
