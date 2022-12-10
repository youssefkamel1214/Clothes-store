package com.youssef.cloath_store.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductFeedback {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userid")
    private int userid;

    @ColumnInfo(name = "productid")
    private int productid;

    @ColumnInfo(name = "rate")
    private int rate;

    @ColumnInfo(name = "feedback")
    private String feedback;

    public ProductFeedback(int userid, int productid, int rate, String feedback) {
        this.userid = userid;
        this.productid = productid;
        this.rate = rate;
        this.feedback = feedback;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

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

    public ProductFeedback(){

    }
}



