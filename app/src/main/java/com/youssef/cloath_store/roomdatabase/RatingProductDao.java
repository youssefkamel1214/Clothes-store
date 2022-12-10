package com.youssef.cloath_store.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.youssef.cloath_store.models.ProductFeedback;
import com.youssef.cloath_store.models.Sales;

import java.util.List;

@Dao
public interface RatingProductDao {
    @Insert
    void insert(ProductFeedback rating);

    @Insert
    void insertll(ProductFeedback... rating);

    @Delete
    void delete(ProductFeedback rating);

    @Update
    void updateUser(ProductFeedback rating);

    @Query("SELECT * FROM ProductFeedback")
    List<ProductFeedback> getall();

    @Query("SELECT * FROM ProductFeedback WHERE userid LIKE :userid")
    List<ProductFeedback> findByUserId(int userid);

    @Query("select AVG(rate) From Productfeedback Where productid= :pid")
     float getavg(int pid);
}
