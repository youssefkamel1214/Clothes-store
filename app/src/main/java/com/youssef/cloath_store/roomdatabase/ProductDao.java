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
    @Insert
    void insertll(List<Product>products);
    @Delete
    void delete(Product product);

    @Update
    void updateProduct(Product product);

    @Query("update product  set amountsold=amountsold+:value , count =count-:value where uid like :id")
    void updatebyidandvalue(int id,int value);

    @Query("SELECT * FROM product WHERE uid LIKE :uid LIMIT 1")
    Product findById(int uid);

    @Query("Delete from product where category=:name")
    void delete_by_categ(String name);

    @Query("select * from product order by  amountsold Desc limit 10")
     List<Product> get_top_10();
    @Query("SELECT * FROM product WHERE title LIKE :title")
    List<Product> findByTitle(String title);
    @Query("SELECT * FROM product where category =:catgory and count > 0")
    List<Product> findByCatogy(String catgory);
    @Query("Select sum(amountsold) from product")
    int get_toatal_sales();
    @Query("SELECT * FROM product")
    List<Product> getAll();
    @Query("update product set category=:s2 where category=:s1")
    void editcatbynames(String s2, String s1);
    @Query("SELECT * FROM product where title like :name  and count > 0")
    List<Product> findByname(String name);
}
