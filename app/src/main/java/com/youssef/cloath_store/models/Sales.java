package com.youssef.cloath_store.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class Sales {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userid")
    private int userid;

    @ColumnInfo(name = "productid")
    private int productid;

    @ColumnInfo(name = "quantity")
    private int quantity;
    @ColumnInfo(name = "date")
    private Calendar date;

    public Sales(int userid, int productid, int quantity, Calendar date) {
        this.userid = userid;
        this.productid = productid;
        this.quantity = quantity;
        this.date = date;
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

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }


}


