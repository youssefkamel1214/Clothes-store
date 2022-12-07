package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.youssef.cloath_store.databinding.ActivityProductDescriptionBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;
import com.youssef.cloath_store.roomdatabase.UserDao;

public class Product_DescriptionActivity extends AppCompatActivity {
    ActivityProductDescriptionBinding binding;
    int Pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));

        ProductDao prod = MyRoomDatabase.getInstance(this).productDao();
        Pid = getIntent().getIntExtra("id", -1);
        new Thread(() -> {
            Product p = prod.findById(Pid);
            Bitmap bmp = BitmapFactory.decodeByteArray(p.getImage(),0,p.getImage().length);
            runOnUiThread(() -> {
                binding.imgProduct.setImageBitmap(bmp);
                binding.ProductName.setText(p.getTitle());
            });
        }).start();

        handletextavailablity();
    }

    private void handletextavailablity() {
        binding.Small.setOnClickListener(View ->{
            View.setBackgroundDrawable(getDrawable(R.drawable.button_background));
            binding.Large.setBackgroundColor(Color.TRANSPARENT);
            binding.XLarge.setBackgroundColor(Color.TRANSPARENT);
            binding.Medium.setBackgroundColor(Color.TRANSPARENT);
        });
        binding.Medium.setOnClickListener(View ->{
            View.setBackgroundDrawable(getDrawable(R.drawable.button_background));
            binding.Large.setBackgroundColor(Color.TRANSPARENT);
            binding.XLarge.setBackgroundColor(Color.TRANSPARENT);
            binding.Small.setBackgroundColor(Color.TRANSPARENT);
        });
        binding.Large.setOnClickListener(View ->{
            View.setBackgroundDrawable(getDrawable(R.drawable.button_background));
            binding.Small.setBackgroundColor(Color.TRANSPARENT);
            binding.XLarge.setBackgroundColor(Color.TRANSPARENT);
            binding.Medium.setBackgroundColor(Color.TRANSPARENT);
        });
        binding.XLarge.setOnClickListener(View ->{
            View.setBackgroundDrawable(getDrawable(R.drawable.button_background));
            binding.Small.setBackgroundColor(Color.TRANSPARENT);
            binding.Large.setBackgroundColor(Color.TRANSPARENT);
            binding.Medium.setBackgroundColor(Color.TRANSPARENT);
        });
        binding.black.setOnClickListener(View ->{
            View.setBackgroundDrawable(getDrawable(R.drawable.button_background));
            binding.White.setBackgroundColor(Color.TRANSPARENT);
            binding.Brown.setBackgroundColor(Color.TRANSPARENT);
        });
        binding.White.setOnClickListener(View ->{
            View.setBackgroundDrawable(getDrawable(R.drawable.button_background));
            binding.black.setBackgroundColor(Color.TRANSPARENT);
            binding.Brown.setBackgroundColor(Color.TRANSPARENT);
        });
        binding.Brown.setOnClickListener(View ->{
            View.setBackgroundDrawable(getDrawable(R.drawable.button_background));
            binding.White.setBackgroundColor(Color.TRANSPARENT);
            binding.black.setBackgroundColor(Color.TRANSPARENT);
        });
    }
}