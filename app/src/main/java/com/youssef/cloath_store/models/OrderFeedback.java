package com.youssef.cloath_store.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class OrderFeedback {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userid")
    private int userid;


    @ColumnInfo(name = "date")
    private Calendar date;

    public OrderFeedback(int userid, Calendar date, int rate, String feedback) {
        this.userid = userid;
        this.date = date;
        this.rate = rate;
        this.feedback = feedback;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @ColumnInfo(name = "rate")
    private int rate;

    @ColumnInfo(name = "feedback")
    private String feedback;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
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

    public OrderFeedback(){}
}
