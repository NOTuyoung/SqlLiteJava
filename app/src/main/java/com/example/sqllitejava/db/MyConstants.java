package com.example.sqllitejava.db;

public class MyConstants {
    public  static final String TABLE_NAME = "my_table";
    public  static final String _ID = "_id";


    public  static final String TRANSLATION1 = "translation1";
    public  static final String TRANSLATION2  = "translation2";
    public  static final String DESCRIPTION  = "description";
    public  static final String AUDIO  = "audioPath";
    public  static final String TRANSCRIPTION  = "transcription";

    public  static final String DB_NAME  = "my_db.db";
    public  static final int DB_VERSION = 3;
    public  static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + TRANSLATION1 + " TEXT," + TRANSLATION2 + " TEXT," +
            DESCRIPTION + " TEXT," + AUDIO + " TEXT," + TRANSCRIPTION + " TEXT)";
    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
