package com.youssef.cloath_store.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.youssef.cloath_store.databinding.ActivityEditCatBinding;
import com.youssef.cloath_store.roomdatabase.CategoriesDao;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

public class EditCatActivity extends AppCompatActivity {
    ActivityEditCatBinding binding;
    ProductDao productDao;
    CategoriesDao categoriesDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        productDao= MyRoomDatabase.getInstance(this).productDao();
        categoriesDao= MyRoomDatabase.getInstance(this).categoriesDao();
        binding.del.setOnClickListener(view -> {
            String s1=binding.idDelete.getText().toString();
            String s2=binding.idDelete2.getText().toString();
            new Thread(()-> {
                categoriesDao.editcatbynames(s2, s1);
                productDao.editcatbynames(s2,s1);
            }).start();
        });
    }
}