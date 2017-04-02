package com.example.chongtian.catching_game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class Result extends AppCompatActivity {

    private static final String TAG="Result Activity";
    private static FileOutputStream outputStream;
    private static int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel =(TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel =(TextView) findViewById(R.id.highScoreLabel);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getInt("SCORE");


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
            Log.e(TAG,"no intent found");
        }

        //write score to an internal file
        File file = new File(getFilesDir(), "result.txt");
        try {
            outputStream = new FileOutputStream (file);
            outputStream.write(score);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
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
