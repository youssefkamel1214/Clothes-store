package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.youssef.cloath_store.databinding.ActivityProductDescriptionBinding;

public class Product_DescriptionActivity extends AppCompatActivity {
    ActivityProductDescriptionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.text.setText(Integer.toString(getIntent().getIntExtra("id", -1)));
    }
}