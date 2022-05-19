package com.example.EasyLearn.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.EasyLearn.containers.ShortTable;
import com.example.EasyLearn.containers.Word;
import com.example.EasyLearn.db.constants.TableModules;
import com.example.EasyLearn.db.constants.TableModules_Words;
import com.example.EasyLearn.db.constants.TableWords;

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


    public  void  InsertWord(String transl1,String transl2,String description,String transcript,String audiopath)
    {
        ContentValues cv = new ContentValues();
        cv.put(TableWords.TRANSLATION1,transl1);
        cv.put(TableWords.TRANSLATION2,transl2);
        cv.put(TableWords.DESCRIPTION,description);
        cv.put(TableWords.TRANSCRIPTION,transcript);
        cv.put(TableWords.AUDIO,audiopath);
        db.insert(TableWords.TABLE_NAME,null,cv);
    }
    public  void  InsertModule(String Name)
    {
        ContentValues cv = new ContentValues();
        cv.put(TableModules.NAME,Name);
        db.insert(TableModules.TABLE_NAME,null,cv);
    }
    public  void  InsertModules_Words(String model_id,String word_id)
    {
        ContentValues cv = new ContentValues();
        cv.put(TableModules_Words.MODULEID,model_id);
        cv.put(TableModules_Words.WORDID,word_id);
        db.insert(TableModules_Words.TABLE_NAME,null,cv);
    }

    public boolean RemoveWord(String id)
    {
        return db.delete(TableWords.TABLE_NAME, TableWords._ID + "='" + id +"' ;", null) > 0;
    }
    public boolean RemoveModule(String id)
    {
        return db.delete(TableModules.TABLE_NAME, TableModules._ID + "='" + id +"' ;", null) > 0;
    }
    public boolean RemoveModules_Words(String id)
    {
        return db.delete(TableModules_Words.TABLE_NAME, TableModules_Words._ID + "='" + id +"' ;", null) > 0;
    }

    public boolean UpdateWord(String id, String transl1, String transl2, String description, String transcript, String audiopath)
    {
        ContentValues cv = new ContentValues();
        cv.put(TableWords.TRANSLATION1,transl1);
        cv.put(TableWords.TRANSLATION2,transl2);
        cv.put(TableWords.DESCRIPTION,description);
        cv.put(TableWords.TRANSCRIPTION,transcript);
        cv.put(TableWords.AUDIO,audiopath);

        return db.update(TableWords.TABLE_NAME,cv,TableWords._ID + "=" + id,null) > 0;

    }
    public boolean UpdateModule(String id, String Name)
    {
        ContentValues cv = new ContentValues();
        cv.put(TableModules.NAME,Name);

        return db.update(TableModules.TABLE_NAME,cv,TableModules._ID + "=" + id,null) > 0;

    }
    public boolean UpdateModules_Words(String id,String model_id,String word_id)
    {
        ContentValues cv = new ContentValues();
        cv.put(TableModules_Words.MODULEID,model_id);
        cv.put(TableModules_Words.WORDID,word_id);

        return db.update(TableModules_Words.TABLE_NAME,cv,TableModules_Words._ID + "=" + id,null) > 0;

    }

    public Word getWord(String id)
    {
        Word word = new Word();
        Cursor cursor = db.query(TableWords.TABLE_NAME, null, TableWords._ID+" = ?",
                new String[] {id}, null, null, TableWords.TRANSLATION1);
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

    public List<ShortTable> getShortWords()
    {
        List<ShortTable> tempList = new ArrayList<>();
        Cursor cursor = db.query(TableWords.TABLE_NAME, new String[]{ TableWords._ID, TableWords.TRANSLATION1}, null,
                null, null, null, null);
        while (cursor.moveToNext())
        {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(TableWords._ID));
            String text = cursor.getString(cursor.getColumnIndexOrThrow(TableWords.TRANSLATION1));
            tempList.add(new ShortTable(id,text));
        }
        cursor.close();
        return  tempList;
    }

    public List<ShortTable> getShortModules()
    {
        List<ShortTable> tempList = new ArrayList<>();
        Cursor cursor = db.query(TableModules.TABLE_NAME, new String[]{ TableModules._ID, TableModules.NAME}, null,
                null, null, null, null);
        while (cursor.moveToNext())
        {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(TableModules._ID));
            String text = cursor.getString(cursor.getColumnIndexOrThrow(TableModules.NAME));
            tempList.add(new ShortTable(id,text));
        }
        cursor.close();
        return  tempList;
    }



    public void ClearTables()
    {
        db.delete(TableWords.TABLE_NAME,null,null);
        db.delete(TableModules.TABLE_NAME,null,null);
        db.delete(TableModules_Words.TABLE_NAME,null,null);

    }

    public  void  closeDb()
    {
        myDbHelper.close();
    }

}
