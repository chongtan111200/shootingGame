package com.example.chongtian.catching_game;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Display;

import java.util.Timer;
import java.util.TimerTask;



public class Main extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView superman;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;

    //Position
    private int supermanY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;


    private int frameHeight;
    private int supermanSize;
    private int screenWidth;
    private int screenHeight;
    private int score=0;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SoundPlayer sound;

    private boolean action_flag = false;
    private boolean start_flag=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new SoundPlayer(this);

        //initialization
        scoreLabel= (TextView) findViewById(R.id.scoreLabel);
        startLabel= (TextView) findViewById(R.id.startLabel);
        superman=(ImageView) findViewById(R.id.superman);
        orange=(ImageView) findViewById(R.id.orange);
        pink=(ImageView) findViewById(R.id.pink);
        black=(ImageView) findViewById(R.id.black);

        //get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenHeight=size.y;
        screenWidth=size.x;



        //move the balls out of the screen
        orange.setX(-100.0f);
        orange.setY(-100.0f);
        pink.setX(-100.0f);
        pink.setY(-100.0f);
        black.setX(-100.0f);
        black.setY(-100.0f);

        //

        supermanY = 500;
        scoreLabel.setText(getString(R.string.score,0));

    }

    public void changePos(){

        hitCheck();

        orangeX-=12;
        if(orangeX<0){
            orangeX=screenWidth +20;
            orangeY=(int) Math.floor(Math.random() * frameHeight - orange.getHeight());
        }
        orange.setX(orangeX);
        orange.setY(orangeY);


        blackX -=16;
        if(blackX <0){
            blackX = screenWidth +10;
            blackY=(int) Math.floor(Math.random() * frameHeight - black.getHeight());
        }
        black.setX(blackX);
        black.setY(blackY);


        pinkX -=26;
        if(pinkX <0){
            pinkX = screenWidth +10;
            pinkY=(int) Math.floor(Math.random() * frameHeight - pink.getHeight());
        }
        pink.setX(pinkX);
        pink.setY(pinkY);





        if(action_flag){
            supermanY -=20;
        }else{
            supermanY +=20;
        }

        if(supermanY < 0) supermanY = 0;

        if(supermanY > frameHeight-supermanSize) supermanY = frameHeight-supermanSize;

        superman.setY(supermanY);

        scoreLabel.setText(getString(R.string.score, score));
    }



    public void hitCheck(){


        int orangeCenterX= orangeX + orange.getWidth()/2;
        int orangeCenterY=orangeY+orange.getHeight()/2;

        if(orangeCenterX>=0 && orangeCenterX <=supermanSize &&
                supermanY <= orangeCenterY && orangeCenterY<=supermanY + supermanSize){
            score+=10;
            orangeX = -10;
            sound.playHitSound();
        }


        int pinkCenterX= pinkX + pink.getWidth()/2;
        int pinkCenterY= pinkY+ pink.getHeight()/2;

        if(pinkCenterX>=0 && pinkCenterX <=supermanSize &&
                supermanY <= pinkCenterY && pinkCenterY<=supermanY + supermanSize){
            score+=30;
            pinkX = -10;
            sound.playHitSound();
        }

        int blackCenterX= blackX + black.getWidth()/2;
        int blackCenterY= blackY+ black.getHeight()/2;
        if(blackCenterX>=0 && blackCenterX <=supermanSize &&
                supermanY <= blackCenterY && blackCenterY<=supermanY + supermanSize){
            sound.playOverSound();
            blackX = -10;
            timer.cancel();
            timer.purge();

            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);
        }
    }




    //control the motion of the superman
    @Override
    public boolean onTouchEvent(MotionEvent event){

        if(!start_flag){
            start_flag=true;

            FrameLayout frame=(FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            supermanY = (int)superman.getY();

            supermanSize=superman.getHeight();

            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Thread(){
                        @Override
                        public void run(){
                            changePos();
                        }
                    });
                }
            },0,100);


        }else {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                action_flag = true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                action_flag = false;
            }
            superman.setY(supermanY);
        }
        return super.onTouchEvent(event);
    }
}
