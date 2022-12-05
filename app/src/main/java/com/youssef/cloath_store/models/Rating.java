package com.youssef.cloath_store.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Rating {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userid")
    private int userid;

    @ColumnInfo(name = "rate")
    private int rate;

    @ColumnInfo(name = "feedback")
    private String feedback;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Rating(){

    }
}



