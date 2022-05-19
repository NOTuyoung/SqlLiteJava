package com.example.EasyLearn.containers;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Word {

    private Integer _id;
    private String translation1;
    private String translation2;
    private int cntOfRight;
    private String description;
    private String audioPath;
    private String transcription;

    public Word() {

    }

    public Word(int _id,String translation1, String translation2, String description,
                String audioPath, String transcription) {
        this._id = _id;
        this.translation1 = translation1;
        this.translation2 = translation2;
        cntOfRight = 0;
        this.description = description;
        this.audioPath = audioPath;
        this.transcription = transcription;
    }

    public String getTranslation1() {
        return translation1;
    }

    public String getTranslation2() {
        return translation2;
    }

    public int getCntOfRight() {
        return cntOfRight;
    }

    public void increaseCnt() {
        cntOfRight++;
    }

    public void resetCnt() {
        cntOfRight = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return this.translation1 == word.translation1 && this.translation2 == word.translation2;
    }

    // Хер знает зачем нам, но пусть будет
    @Override
    public int hashCode() {
        return Objects.hash(_id, translation1, translation2, cntOfRight, description, audioPath, transcription);
    }

    @NonNull
    @Override
    public String toString() {
        String temp = "translation1 - " +  translation1  + "\ntranslation2  - "  + translation2;
        return  temp;
    }

}
