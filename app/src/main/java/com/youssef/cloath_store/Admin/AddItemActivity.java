package com.youssef.cloath_store.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.youssef.cloath_store.Constants;
import com.youssef.cloath_store.databinding.ActivityAddItemBinding;
import com.youssef.cloath_store.databinding.ActivityTest2Binding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

import java.io.IOException;

public class AddItemActivity extends AppCompatActivity {
    ActivityAddItemBinding binding;
    ProductDao product_add;
    byte []image=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        product_add= MyRoomDatabase.getInstance(this.getApplicationContext()).productDao();
        binding.imageView3.setOnClickListener(view -> {
            ImagePicker.with(this).crop(200, 200).compress(512).maxResultSize(600,600).start();
        });
        binding.additem.setOnClickListener(view -> {

            String title=binding.titleadd2.getText().toString();
            String category=binding.categ2.getText().toString();
            float price=Float.parseFloat(binding.price2.getText().toString());
            int amountSold=Integer.parseInt(binding.sold2.getText().toString());
            int count=Integer.parseInt(binding.count2.getText().toString());
            AddItem(title,category,price,amountSold,count);

        });
    }


    public void AddItem(String title,String category,float price,int amountSold,int count) {
        Product Newprod=new Product();
        Newprod.setTitle(title);
        Newprod.setCategory(category);
        Newprod.setPrice(price);
        Newprod.setCount(count);
        Newprod.setAmountsold(amountSold);
        Newprod.setImage(image);
        new Thread(()->{
            // (insert New product into the db)
            product_add.insert(Newprod);

            // lw 5lst wm7tag t update ui
            runOnUiThread(() -> {
                Toast.makeText(this,"New item has been added",Toast.LENGTH_SHORT).show();
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