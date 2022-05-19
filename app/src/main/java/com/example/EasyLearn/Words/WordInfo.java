package com.example.EasyLearn.Words;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.EasyLearn.R;
import com.example.EasyLearn.containers.Word;
import com.example.EasyLearn.db.DbManager;

public class WordInfo extends AppCompatActivity {

    TextView tv1,tv2;
    DbManager dbManager = new DbManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_info);
        tv1 =  findViewById(R.id.wordinfo_tv1);
        tv2 =  findViewById(R.id.wordinfo_tv2);

        Bundle arguments = getIntent().getExtras();
        String word_id = arguments.get("word_id").toString();
        FillData(word_id);


    }

    private void FillData(String id)
    {
        dbManager.openDb();
        Word word  = dbManager.getWord(id);
        dbManager.closeDb();
        tv1.setText(word.getTranslation1());
        tv2.setText(word.getTranslation2());


    }

}