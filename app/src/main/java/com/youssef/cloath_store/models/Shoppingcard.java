package com.youssef.cloath_store.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Shoppingcard {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userid;
    private int proudctid;
    private int count;

    public Shoppingcard( int userid, int proudctid, int count) {
        this.userid = userid;
        this.proudctid = proudctid;
        this.count = count;
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

    public int getProudctid() {
        return proudctid;
    }

    public void setProudctid(int proudctid) {
        this.proudctid = proudctid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
