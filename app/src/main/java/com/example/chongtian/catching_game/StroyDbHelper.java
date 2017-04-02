package com.example.chongtian.catching_game;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chong tian on 4/1/2017.
 * helper class to set up the database access
 */

class StroyDbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Hero_story";
    private static final String COLUMN_NAME_TITLE = "superhero";
    private static final String COLUMN_STORY_TITLE = "story";

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "MET683homework.db";
    private static final String COLUMN_ID ="_id";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + " TEXT," +COLUMN_STORY_TITLE
                    +" TEXT)";

    private static final String SQL_INSERT_ENTRIES =
            "INSERT INTO " + TABLE_NAME + " VALUES ";

    private static final String STORY1="(" +"'1', "+"'SUPERMAN'" + ", " +
            "'He was born Kal-El on the alien planet Krypton," +
            " before being rocketed to Earth as an infant by his scientist father Jor-El, " +
            "moments before Krypton''s destruction.'"+")";

    private static final String STORY2="(" +"'2', "+"'SPIDERMAN'" + ", " +
            "'Peter Parker character has developed from shy, " +
            "nerdy high school student to troubled but outgoing college student.'"+")";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    StroyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * create data base
     * @param db default database
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    /**
     * upgrade database when version number is larger
     * @param db default database
     * @param oldVersion version number
     * @param newVersion new version number
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
        db.execSQL(SQL_INSERT_ENTRIES+STORY1);
        db.execSQL(SQL_INSERT_ENTRIES+STORY2);
    }

    /**
     * do the opposite of on upgrade
     * @param db default database
     * @param oldVersion version number
     * @param newVersion new version number
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * getter table name
     * @return table name
     */
    String getTableName(){
        return TABLE_NAME;
    }

    /**
     * getter column title
     * @return column title
     */
    String getColumnNameTitle(){
        return COLUMN_NAME_TITLE;
    }

    /**
     * getter column subtitle
     * @return column subtitle, second column
     */
    String getColumnNameSubtitle(){
        return COLUMN_STORY_TITLE;
    }

    /**
     * getter id
     * @return id of the column
     */
    String getColumnId(){
        return COLUMN_ID;
    }

}
