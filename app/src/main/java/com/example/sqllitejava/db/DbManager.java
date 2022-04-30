package com.example.sqllitejava.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqllitejava.Word;

import java.util.ArrayList;
import java.util.List;



public class DbManager {

    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public DbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }

    public  void  openDb()
    {
        db = myDbHelper.getWritableDatabase();
    }
    public  void  InsertToDb(String transl1,String transl2,String description,String transcript,String audiopath)
    {

        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TRANSLATION1,transl1);
        cv.put(MyConstants.TRANSLATION2,transl2);
        cv.put(MyConstants.DESCRIPTION,description);
        cv.put(MyConstants.TRANSCRIPTION,transcript);
        cv.put(MyConstants.AUDIO,audiopath);

        db.insert(MyConstants.TABLE_NAME,null,cv);
    }



    public Word getWordByTranslation1(String value) {

        Word word = new Word();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, "translation1 = ?",
                new String[] {value}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String column1 = cursor.getString(0);
                String column2 = cursor.getString(1);
                String column3 = cursor.getString(2);
                String column4 = cursor.getString(3);
                String column5 = cursor.getString(4);
                String column6 = cursor.getString(5);
                word = new Word(Integer.parseInt(column1),column2,column3,column4 ,column5,column6);
            } while (cursor.moveToNext());
            cursor.close();

        }
        return word;
    }


    public List<String> getTranslations1()
    {
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, null,
                null, null, null, null);
        while (cursor.moveToNext())
        {
            String value1 = cursor.getString(cursor.getColumnIndexOrThrow(MyConstants.TRANSLATION1));
            tempList.add(value1);
        }
        cursor.close();
        return  tempList;
    }
    public List<String> getTranslations2()
    {
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, null,
                null, null, null, null);
        while (cursor.moveToNext())
        {
            String value1 = cursor.getString(cursor.getColumnIndexOrThrow(MyConstants.TRANSLATION2));
            tempList.add(value1);
        }
        cursor.close();
        return  tempList;
    }

    public void ClearTable()
    {
        db.delete(MyConstants.TABLE_NAME,null,null);
    }

    public  void  closeDb()
    {
        myDbHelper.close();
    }

}
