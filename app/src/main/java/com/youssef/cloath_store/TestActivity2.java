package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

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

public class TestActivity2 extends AppCompatActivity {
    ActivityTest2Binding binding;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTest2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bimap =MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                bimap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                Product product=new Product();
                product.setCategory("t-shirt");
                product.setCount(25);
                product.setPrice(250);
                product.setTitle("ahoooo");
                product.setAmountsold(0);
                product.setImage(baos.toByteArray());
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