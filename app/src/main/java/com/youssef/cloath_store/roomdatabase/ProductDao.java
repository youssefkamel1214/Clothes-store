package com.youssef.cloath_store.roomdatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.youssef.cloath_store.models.Product;

import java.util.List;

@Dao
public interface ProductDao{
    @Insert
    void insert(Product product);

    @Insert
    void insertll(Product... product);

    @Delete
    void delete(Product product);

    @Update
    void updateProduct(Product product);

    @Query("SELECT * FROM product WHERE uid LIKE :uid LIMIT 1")
    Product findById(int uid);

    @Query("select * from product order by  amountsold limit 10")
     List<Product> get_top_10();
    @Query("SELECT * FROM product WHERE title LIKE :title")
    List<Product> findById(String title);

    @Query("SELECT * FROM product")
    List<Product> getAll();
}
