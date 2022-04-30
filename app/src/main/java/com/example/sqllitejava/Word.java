package com.example.sqllitejava;

import androidx.annotation.NonNull;

import java.util.List;

public class Word
{
    private Integer _id;
    private String translation1;
    private String translation2;
    private String description;
    private String audioPath;
    private String transcription;

    public Word() {
    }
    public Word(int _id,String translation1, String translation2, String description, String audioPath, String transcription) {
        this._id = _id;
        this.translation1 = translation1;
        this.translation2 = translation2;
        this.description = description;
        this.audioPath = audioPath;
        this.transcription = transcription;
    }

    @NonNull
    @Override
    public String toString() {
        String temp = "translation1 - " +  translation1  + "\ntranslation2  - "  + translation2;
        return  temp;
    }
}
