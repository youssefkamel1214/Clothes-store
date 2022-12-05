package com.youssef.cloath_store.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.youssef.cloath_store.models.Rating;
import com.youssef.cloath_store.models.Sales;

import java.util.List;

@Dao
public interface RatingDao{
    @Insert
    void insert(Rating rating);

    @Insert
    void insertll(Rating... rating);

    @Delete
    void delete(Rating rating);

    @Update
    void updateUser(Rating rating);

    @Query("SELECT * FROM sales WHERE id = :id ")
    Sales findById(int id);

    @Query("SELECT * FROM sales WHERE userid LIKE :userid")
    List<Sales> findByUserId(int userid);


    @Query("SELECT * FROM sales")
    List<Sales> getAll();
}
