package com.example.EasyLearn.db.constants;

public class TableModules {
    public  static final String TABLE_NAME = "Modules";
    public  static final String _ID = "module_id";
    public  static final String NAME = "Name";


    public  static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            _ID + " INTEGER NOT NULL PRIMARY KEY," +
            NAME + " TEXT NOT NULL ) ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + " ";

}
