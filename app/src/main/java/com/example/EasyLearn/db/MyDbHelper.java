package com.example.EasyLearn.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.EasyLearn.db.constants.MyConstants;
import com.example.EasyLearn.db.constants.TableModules;
import com.example.EasyLearn.db.constants.TableModules_Words;
import com.example.EasyLearn.db.constants.TableWords;

public class MyDbHelper extends SQLiteOpenHelper {


    public MyDbHelper(@Nullable Context context) {
        super(context, MyConstants.DB_NAME, null, MyConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableWords.TABLE_STRUCTURE );
        db.execSQL(TableModules.TABLE_STRUCTURE );
        db.execSQL(TableModules_Words.TABLE_STRUCTURE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldver, int newver) {
        db.execSQL(TableWords.DROP_TABLE);
        db.execSQL(TableModules.DROP_TABLE);
        db.execSQL(TableModules_Words.DROP_TABLE);
        onCreate(db);
    }
}
