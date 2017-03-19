package com.example.chongtian.catching_game;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.chongtian.catching_game.R;

/**
 * Created by chong tian on 3/17/2017.
 * plays sound when event happens
 */

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;

    public SoundPlayer(Context context){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);

        hitSound = soundPool.load(context, R.raw.hit,1);
        overSound = soundPool.load(context,R.raw.over,1);
    }

    public void playHitSound(){
        soundPool.play(hitSound,1.0f,1.0f,1,1,1.0f);

    }

    public void playOverSound(){
        soundPool.play(overSound,1.0f,1.0f,1,1,1.0f);

    }
}
