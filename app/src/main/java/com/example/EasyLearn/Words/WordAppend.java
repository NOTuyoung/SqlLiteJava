package com.example.EasyLearn.Words;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.EasyLearn.R;
import com.example.EasyLearn.db.DbManager;
import com.google.android.material.snackbar.Snackbar;

public class WordAppend extends AppCompatActivity {

    EditText editText1, editText2;
    DbManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_append);
        dbManager = new DbManager(this);
        editText1 = (EditText) findViewById(R.id.editTextFirst);
        editText2 = (EditText) findViewById(R.id.editTextSecond);
        editText2.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    AppendWord();

                    return true;
                }
                return false;
            }
        });

    }

    public void onClickAppend(View view) {
        AppendWord();

    }
    private void AppendWord()
    {
        if (!checkFields()) return;
        dbManager.openDb();
        dbManager.InsertWord(editText1.getText().toString(),editText2.getText().toString(),null,null,null);
        dbManager.closeDb();
        WordList.isWordAppend = true;
        PrintMessage("Слово "+ editText1.getText().toString() +" успешно добавлено");
        //this.finish();
        Clear();
    }


    private boolean checkFields() {
        String text1 = editText1.getText().toString();
        String text2 = editText2.getText().toString();

        boolean flag = false;
        if(text1.equals("")) {
            editText1.setHintTextColor(Color.RED);
            flag = true;
        }
        if(text2.equals("")) {
            editText2.setHintTextColor(Color.RED);
            flag = true;
        }

        if(flag) {
            PrintMessage(getString(R.string.Error_emptyEditText));
            return false;
        }

        return  true;
    }

    @SuppressLint("ResourceAsColor")
    private void Clear() {
        editText1.setText("");
        editText2.setText("");
        editText1.setHintTextColor(R.color.hint_foreground_material_light);
        editText2.setHintTextColor(R.color.hint_foreground_material_light);
    }

    private void PrintMessage(String text) {
        View view = this.findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(0XFF555555);
        snackbar.setActionTextColor(0XFF0277BD);
        snackbar.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}