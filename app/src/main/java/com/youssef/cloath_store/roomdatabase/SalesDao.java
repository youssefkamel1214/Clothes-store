package com.youssef.cloath_store.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.youssef.cloath_store.models.Sales;

import java.util.List;

@Dao
public interface SalesDao{
    @Insert
    void insert(Sales sale);

    @Insert
    void insertll(Sales... sale);

    @Delete
    void delete(Sales sale);

    @Update
    void updateUser(Sales sale);

    @Query("SELECT * FROM sales WHERE userid LIKE :userid and date like :date")
    List<Sales> findByIserId(int userid,long date);

    @Query("SELECT * FROM sales WHERE date = :date")
    List<Sales> findByDate(long date);


//    @Query("SELECT * FROM sales WHERE productid LIKE :productid LIMIT 1")
//    Sales findByProductId(int productid);

    @Query("SELECT * FROM sales")
    List<Sales> getAll();
}
