package com.youssef.cloath_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.youssef.cloath_store.authintication.SigninActivity;
import com.youssef.cloath_store.card.Shopping_cart_Fragment;
import com.youssef.cloath_store.databinding.ActivityMainBinding;
import com.youssef.cloath_store.home.HomeFragment;
import com.youssef.cloath_store.models.User;
import com.youssef.cloath_store.product.ProductFragment;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.UserDao;
import com.youssef.cloath_store.search.SearchFragment;
import com.youssef.cloath_store.profile.UserFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int id;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.log_out)
        {
            Intent i = new Intent(this, SigninActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            sharedPreferences.edit().putInt(Constants.RememberValue,-1).apply();
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        id=getIntent().getIntExtra("id",-1);
        UserDao userDao= MyRoomDatabase.getInstance(this).userdao();
        new Thread(()->{
            User user=userDao.findById(id);
            Calendar calendar=Calendar.getInstance();
            if(checkifdayandmonth(calendar,user.getDate())){
               runOnUiThread(()->{
                   Dialog dialog = new Dialog(MainActivity.this);
                   dialog.setContentView(R.layout.custom_bar_dialog);
                   TextView textView=dialog.findViewById(R.id.continuetxt);
                   textView.setOnClickListener(view -> dialog.dismiss());
                   dialog.show();
               });
            }
        }).start();
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_tag) instanceof ProductFragment)
                binding.bottomnav.setVisibility(View.GONE);
            else
                binding.bottomnav.setVisibility(View.VISIBLE);
        });
        if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_tag) instanceof ProductFragment){
            binding.bottomnav.setVisibility(View.GONE);
        }
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
                    bundle.putInt("id",id);
                    F.setArguments(bundle);
                    break;
                case R.id.search:
                    F = new SearchFragment();
                    break;
                case R.id.shopping_cart:
                    F = new Shopping_cart_Fragment();
                    Bundle bundlee = new Bundle();
                    bundlee.putInt("id",id);
                    F.setArguments(bundlee);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,F).commit();
            return true;
        });

    }

    private boolean checkifdayandmonth(Calendar calendar, Calendar date) {
        int day1=calendar.get(Calendar.DAY_OF_MONTH),day2=date.get(Calendar.DAY_OF_MONTH);
        int month1=calendar.get(Calendar.MONTH),month2=date.get(Calendar.MONTH);
        if(day1==day2&&month1==month2)
            return  true;
        else
            return false;
    }


}