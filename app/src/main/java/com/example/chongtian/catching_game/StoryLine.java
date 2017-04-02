package com.example.chongtian.catching_game;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoryLine extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_line);
        lv = (ListView) findViewById(R.id.storyList);

        StroyDbHelper mDbHelper = new StroyDbHelper(getApplicationContext());
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String query = "SELECT * FROM "+mDbHelper.getTableName();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, cursor);
        // Attach cursor adapter to the ListView
        lv.setAdapter(todoAdapter);

        db.close();
    }
}
