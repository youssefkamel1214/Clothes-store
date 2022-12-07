package com.youssef.cloath_store.roomdatabase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.Shoppingcard;

import java.util.List;

@Dao
public interface ShoppingDao {
    @Insert(onConflict = REPLACE)
     void insert(Shoppingcard shoppingcard);
    @Update
    void updatell(List<Shoppingcard> list);
    @Query("select * from Shoppingcard where userid =:userid")
    List<Shoppingcard> getbyuserid(int userid);
    @Delete
    void delete(Shoppingcard shoppingcard);
    @Delete
    void delete(List<Shoppingcard>shoppingcards);
}
