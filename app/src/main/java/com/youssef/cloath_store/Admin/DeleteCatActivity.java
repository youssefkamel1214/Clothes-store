package com.youssef.cloath_store.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.youssef.cloath_store.databinding.ActivityDeleteCatBinding;
import com.youssef.cloath_store.models.Categories;
import com.youssef.cloath_store.roomdatabase.CategoriesDao;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

public class DeleteCatActivity extends AppCompatActivity {
ActivityDeleteCatBinding binding;
CategoriesDao cat_daodel;
ProductDao prod_del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDeleteCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cat_daodel= MyRoomDatabase.getInstance(this).categoriesDao();
        prod_del= MyRoomDatabase.getInstance(this).productDao();
        Categories cat_del=new Categories();

        binding.del.setOnClickListener(view -> {

        String name =binding.idDelete.getText().toString().trim() ;
        new Thread(()->{
            cat_daodel.delete_byname(name);
            prod_del.delete_by_categ(name);

        }).start();


        });

    }
}