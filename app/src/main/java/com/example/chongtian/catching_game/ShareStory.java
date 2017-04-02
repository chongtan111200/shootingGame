package com.example.chongtian.catching_game;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShareStory extends AppCompatActivity {

    private EditText nameText;
    private EditText storyText;
    private Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_story);
        nameText= (EditText) findViewById(R.id.superheroName);
        storyText= (EditText) findViewById(R.id.superheroStory);
        shareButton = (Button) findViewById(R.id.addStoryButton);
    }

    //trigger by the button add to add story
    void addStory(View view){
        String name=nameText.getText().toString();
        String story=storyText.getText().toString();

        ContentValues cv=new ContentValues();

        cv.put("superhero",name);
        cv.put("story",story);

        getContentResolver().insert(StoryProvider.CONTENT_URI,cv);
    }
}
