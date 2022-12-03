package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.youssef.cloath_store.ViewHolder.Categories;
import com.youssef.cloath_store.ViewHolder.CategoryAdapter;
import com.youssef.cloath_store.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private CategoryAdapter CAdapter;
    private ArrayList<Categories> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = findViewById(R.id.Rv_Categories);
        categories = new ArrayList<Categories>();

        String[] Cat = {"T-Shirts", "Pants", "Shoes","Jackets"};
        int[] images = {R.drawable.shirts,R.drawable.pants,R.drawable.wonder,R.drawable.jackets};

        for(int i = 0; i < 4;i++)
            categories.add(new Categories(Cat[i],images[i]));

        CAdapter = new CategoryAdapter(this, categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(CAdapter);
    }
}