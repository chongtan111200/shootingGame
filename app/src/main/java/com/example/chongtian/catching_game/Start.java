package com.example.chongtian.catching_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    /**
     * to be called when the start button is clicked
     * start the main activity and add the fragment
     * @param view
     */
    void startGame(View view){
        startActivity(new Intent(getApplicationContext(), Main.class));
    }

    /**
     * to be called when the story button is clicked
     * start the story line view activity
     * @param view
     */
    void startStory(View view){
        startActivity(new Intent(getApplicationContext(), StoryLine.class));
    }

    /**
     * to be called when the share story button is clicked
     * start the share story activity
     * @param view
     */
    void startShareStory(View view){
        startActivity(new Intent(getApplicationContext(), ShareStory.class));
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }

}
