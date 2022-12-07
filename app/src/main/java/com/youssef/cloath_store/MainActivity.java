package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.youssef.cloath_store.card.Shopping_cart_Fragment;
import com.youssef.cloath_store.databinding.ActivityMainBinding;
import com.youssef.cloath_store.home.HomeFragment;
import com.youssef.cloath_store.product.ProductFragment;
import com.youssef.cloath_store.search.SearchFragment;
import com.youssef.cloath_store.profile.UserFragment;

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

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view_tag, new HomeFragment()).commit();
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_tag) instanceof ProductFragment)
                binding.bottomnav.setVisibility(View.GONE);
            else
                binding.bottomnav.setVisibility(View.VISIBLE);
        });
        binding.bottomnav.setSelectedItemId(R.id.home);
        binding.bottomnav.setOnItemSelectedListener(item -> {
            Fragment F = null;
            switch(item.getItemId()){
                case R.id.home:
                    F = new HomeFragment();
                    break;
                case R.id.user:
                    F = new UserFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",getIntent().getIntExtra("id",-1));
                    F.setArguments(bundle);
                    break;
                case R.id.search:
                    F = new SearchFragment();
                    break;
                case R.id.shopping_cart:
                    F = new Shopping_cart_Fragment();
                    Bundle bundlee = new Bundle();
                    bundlee.putInt("id",getIntent().getIntExtra("id",-1));
                    F.setArguments(bundlee);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,F).commit();
            return true;
        });

    }
}