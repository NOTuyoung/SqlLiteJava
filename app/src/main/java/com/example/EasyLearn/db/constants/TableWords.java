package com.example.EasyLearn.db.constants;

public class TableWords {

    public  static final String TABLE_NAME = "Words";
    public  static final String _ID = "word_id";
    public  static final String TRANSLATION1 = "translation1";
    public  static final String TRANSLATION2  = "translation2";
    public  static final String DESCRIPTION  = "description";
    public  static final String AUDIO  = "audio";
    public  static final String TRANSCRIPTION  = "transcription";


    public  static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            _ID + " INTEGER NOT NULL PRIMARY KEY," +
            TRANSLATION1 + " TEXT NOT NULL ," +
            TRANSLATION2 + " TEXT NOT NULL ," +
            DESCRIPTION + " TEXT," +
            AUDIO + " TEXT," +
            TRANSCRIPTION + " TEXT) ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + " ";

}
