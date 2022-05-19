package com.example.EasyLearn.containers;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ShortTable {

    String id;
    String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShortTable(String id, String text) {
        this.id = id;
        this.text = text;
    }


    @NonNull
    @Override
    public String toString() {
        return text;
    }
}
