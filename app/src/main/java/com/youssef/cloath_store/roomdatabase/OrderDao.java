package com.youssef.cloath_store.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.youssef.cloath_store.models.OrderFeedback;
import com.youssef.cloath_store.models.Product;

import java.util.List;
@Dao
public interface OrderDao {

    @Insert
    void insert(OrderFeedback orderFeedback);

    @Query("SELECT * FROM OrderFeedback WHERE userid LIKE :uid")
    List<OrderFeedback> findById(int uid);

    @Query("SELECT * FROM OrderFeedback")
    List<OrderFeedback> Getall();

}
