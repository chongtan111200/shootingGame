package com.example.chongtian.catching_game;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chong tian on 4/1/2017.
 */

public class StoryProvider extends ContentProvider {
    private static final String AUTHORITY =
            "com.chongtian.catching_game.MyContentProvider";
    private static final String STORY_TABLE = "Hero_story";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + STORY_TABLE);

    public static final int STORIES = 1;


    StroyDbHelper storyDbHelper;
    SQLiteDatabase db;

    private static final UriMatcher sURIMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, STORY_TABLE, STORIES);
    }


    @Override
    public boolean onCreate() {
        storyDbHelper=new StroyDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        int uriType = sURIMatcher.match(uri);
        if(uriType==STORIES) {
            queryBuilder.setTables(storyDbHelper.getTableName());

            db = storyDbHelper.getWritableDatabase();
            Cursor cursor = queryBuilder.query(storyDbHelper.getReadableDatabase(),
                    projection, selection, selectionArgs, null, null,
                    sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(),
                    uri);
            return cursor;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int uriType = sURIMatcher.match(uri);

        storyDbHelper.getWritableDatabase();
        if(uriType==STORIES){
            db = storyDbHelper.getWritableDatabase();
            db.insert(storyDbHelper.getTableName(),null,values);
        }
        db.close();
        try {
            getContext().getContentResolver().notifyChange(uri, null);
        }catch(NullPointerException e){
            Log.e("content provider","getContentResolver error");
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
