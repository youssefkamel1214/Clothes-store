package com.youssef.cloath_store.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Categories {
    public Categories() {
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "title")
    private String  Title;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public Categories(String title, byte[] image) {
        Title = title;
        this.image = image;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
