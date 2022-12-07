package com.youssef.cloath_store;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.youssef.cloath_store.databinding.ActivityTest2Binding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.Rating;
import com.youssef.cloath_store.models.Sales;
import com.youssef.cloath_store.models.User;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;
import com.youssef.cloath_store.roomdatabase.RatingDao;
import com.youssef.cloath_store.roomdatabase.SalesDao;
import com.youssef.cloath_store.roomdatabase.UserDao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class TestActivity2 extends AppCompatActivity {
    ActivityTest2Binding binding;
    public static final int PICK_IMAGE = 1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTest2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(view -> {
            ImagePicker.with(this)
                    .compress(2^8)
                    .crop(200, 200)
                    .start(PICK_IMAGE)  ;
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            try {

                Product product=new Product();
                product.setCategory("t-shirt");
                product.setCount(25);
                product.setPrice(250);
                product.setTitle("ahoooo");
                product.setAmountsold(0);
                product.setImage(Constants.getBytes(data.getData(), this));
               ProductDao pr= MyRoomDatabase.getInstance(this.getApplicationContext()).productDao();
               new Thread(()->{
                   pr.insert(product);
                   Product pa=pr.getAll().get(0);
                   runOnUiThread(() -> {
                       Bitmap bmp = BitmapFactory.decodeByteArray(pa.getImage(), 0,pa.getImage().length);
                       binding.imageView.setImageBitmap(bmp);
                   });
               }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}