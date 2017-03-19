package com.example.chongtian.catching_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel =(TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel =(TextView) findViewById(R.id.highScoreLabel);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int score = extras.getInt("SCORE");


        scoreLabel.setText(getString(R.string.score,score));

        SharedPreferences settings = getSharedPreferences("GAME_DATA",MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE",0);

        if(score > highScore){
            highScoreLabel.setText(getString(R.string.highscore,score));

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.apply();
        }else{
            highScoreLabel.setText(getString(R.string.highscore,score));
        }
        }else{
            Log.e("Result","no intent found");
        }
    }

    /**
     * start the game again
     * @param view view
     */
    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(),Main.class));
    }
}
