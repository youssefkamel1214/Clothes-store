package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.youssef.cloath_store.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        binding.bottomnav.setSelectedItemId(R.id.home);
        binding.bottomnav.setOnItemSelectedListener(item -> {
            Fragment F = null;
            switch(item.getItemId()){
                case R.id.home:
                    F = new HomeFragment();
                    break;
                case R.id.user:
                    F = new UserFragment();
                    break;
                case R.id.search:
                    F = new SearchFragment();
                    break;
                case R.id.shopping_cart:
                    F = new Shopping_cart_Fragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,F).commit();
            return true;
        });

    }
}