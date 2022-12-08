package com.youssef.cloath_store.authintication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.ActivityForgetMeBinding;
import com.youssef.cloath_store.databinding.ActivitySigninBinding;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.UserDao;

import java.util.Objects;

public class ForgetMeActivity extends AppCompatActivity {
    ActivityForgetMeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        binding=ActivityForgetMeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button2.setOnClickListener(View -> {
            UserDao users = MyRoomDatabase.getInstance(this).userdao();
            new Thread(()->{
                String pw = users.ForgetPW(binding.Email.getText().toString(),binding.Phoneno.getText().toString());
                runOnUiThread(()->{
                    if(pw == null || pw.isEmpty() )
                    {
                        Toast. makeText(getApplicationContext(),"Invalid Email Or phoneNo",Toast. LENGTH_SHORT).show();
                    }
                    else{
                        Toast. makeText(getApplicationContext(),pw,Toast. LENGTH_SHORT).show();
                        finish();
                    }
                });

            }).start();


        });


    }
}