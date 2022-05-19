package com.example.EasyLearn.db.constants;

public class TableModules_Words {


    public static final String TABLE_NAME = "Modules_Words";
    public  static final String _ID = "_id";
    public static final String MODULEID = "module_id";
    public static final String WORDID = "word_id";

    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY NOT NULL,"
            + MODULEID + " INTEGER,"
            + WORDID + " INTEGER," +
            "FOREIGN KEY (" + MODULEID + ") REFERENCES Modules (" + MODULEID + ")" +
            "FOREIGN KEY (" + WORDID + ")   REFERENCES Words  (" + WORDID + ")" +
            ") ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + " ";


}
