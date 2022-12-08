package com.youssef.cloath_store.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.youssef.cloath_store.models.Categories;

import java.util.List;

@Dao
public interface CategoriesDao {

    @Insert
    void insert(Categories categories);
    @Insert
    void insertall(List<Categories> categories);
    @Update
    void update(Categories categories);
    @Delete
    void delete(Categories categories);
    @Query("Delete from Categories where title = :name")
    void delete_byname(String name);
    @Query("select * from categories ")
    List<Categories> getall();
    @Query("update categories set title=:newname where  title=:oldname")
    void editcatbynames(String newname,String oldname);
}
