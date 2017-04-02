package com.example.chongtian.catching_game;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.example.chongtian.catching_game.R;

/**
 * Created by chong tian on 3/17/2017.
 * plays sound when event happens
 */

class SoundPlayer {

    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private static int backgroundMusic;

    SoundPlayer(Context context){
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC,0);

        hitSound = soundPool.load(context, R.raw.hit,1);
        overSound = soundPool.load(context,R.raw.over,1);
        backgroundMusic=soundPool.load(context,R.raw.background,1);
    }

     void playHitSound(){
        soundPool.play(hitSound,1.0f,1.0f,1,1,1.0f);

    }

    void playOverSound(){
        soundPool.play(overSound,1.0f,1.0f,1,1,1.0f);

    }

    void playBackground(){
        soundPool.play(backgroundMusic,1.0f,1.0f,1,1,1.0f);
    }


    void pauseBackground(){
        soundPool.autoPause();
    }

    void resumeBackground(){
        soundPool.play(backgroundMusic,1.0f,1.0f,1,1,1.0f);
    }
}
