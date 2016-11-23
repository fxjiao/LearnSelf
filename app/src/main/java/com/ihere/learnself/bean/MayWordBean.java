package com.ihere.learnself.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by fxjiao on 16/2/23.
 */
@DatabaseTable(tableName = "tb_may_words")
public class MayWordBean {
    @DatabaseField(columnName = "id",generatedId = true)
    private int id;
    @DatabaseField(columnName = "word")
    private String word;
    @DatabaseField(columnName = "wordTranslate")
    private String wordTranslate;
    @DatabaseField(columnName = "phonogram")
    private String phonogram;
    @DatabaseField(columnName = "sentence")
    private String sentence;
    @DatabaseField(columnName = "sentenceTranslate")
    private String sentenceTranslate;
    @DatabaseField(columnName = "level")
    private String level;

    @DatabaseField(columnName = "isNewWord",dataType= DataType.BOOLEAN)
    private boolean isNewWord;

    public String getSentenceTranslate() {
        return sentenceTranslate;
    }

    public void setSentenceTranslate(String sentenceTranslate) {
        this.sentenceTranslate = sentenceTranslate;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordTranslate() {
        return wordTranslate;
    }

    public void setWordTranslate(String wordTranslate) {
        this.wordTranslate = wordTranslate;
    }

    public String getPhonogram() {
        return phonogram;
    }

    public void setPhonogram(String phonogram) {
        this.phonogram = phonogram;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isNewWord() {
        return isNewWord;
    }

    public void setIsNewWord(boolean isNewWord) {
        this.isNewWord = isNewWord;
    }


}
