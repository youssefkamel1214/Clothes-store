package com.youssef.cloath_store.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.youssef.cloath_store.Constants;
import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.ActivityAddCatBinding;
import com.youssef.cloath_store.models.Categories;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.roomdatabase.CategoriesDao;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

import java.io.IOException;

public class AddCatActivity extends AppCompatActivity {
    ActivityAddCatBinding binding;
    CategoriesDao catg_add;
    byte []image=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        binding= ActivityAddCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        catg_add= MyRoomDatabase.getInstance(this.getApplicationContext()).categoriesDao();
        binding.imageView3.setOnClickListener(view -> {
            ImagePicker.with(this).crop(200, 200).compress(512).maxResultSize(600,600).start();
        });
        binding.additem.setOnClickListener(view -> {

            String title=binding.titleadd2.getText().toString();
            AddItem(title);

        });

    }


    public void AddItem(String title) {
        Categories Newcat=new Categories();
        Newcat.setTitle(title);
        Newcat.setImage(image);
        new Thread(()->{
            // (insert New product into the db)
            catg_add.insert(Newcat);

            // lw 5lst wm7tag t update ui
            runOnUiThread(() -> {
                Toast.makeText(this,"New item has been added",Toast.LENGTH_SHORT).show();
                finish();
            });


        }).start();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                binding.imageView3.setImageBitmap(bitmap);
                image= Constants.getBytes(data.getData(), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}