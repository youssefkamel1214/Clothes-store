package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.youssef.cloath_store.databinding.ActivitySigninBinding;

import java.util.Objects;

public class SigninActivity extends AppCompatActivity {
    ActivitySigninBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        // return user id
        if(sharedPreferences.getInt(Constants.RememberValue,-1)!=-1){
            movetohome(sharedPreferences.getInt(Constants.RememberValue,-1));
        }
        binding.signin.setOnClickListener(view -> {
            if(binding.rembemer.isChecked()) {
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putInt(Constants.RememberValue, 0);
                myEdit.apply();
            }
            movetohome(0);
        });
    }

    private void movetohome(int id) {
            Intent i=new Intent(SigninActivity.this,MainActivity.class);
            i.putExtra("id",id);
            startActivity(i);
            finish();
    }
}