package com.youssef.cloath_store.authintication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.youssef.cloath_store.Activities.Product_infoActivity;
import com.youssef.cloath_store.Constants;
import com.youssef.cloath_store.MainActivity;
import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.ActivitySigninBinding;
import com.youssef.cloath_store.models.User;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.UserDao;

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
            String [] info = new String[2];
            info[0] = binding.Email.getText().toString();
            info[1] = binding.password.getText().toString();
            for(int i = 0; i < 2;i++)
            {
                if(info[i] == null || info[i].isEmpty())
                {
                    Toast. makeText(getApplicationContext(),"Please Fill the Requirements",Toast. LENGTH_SHORT).show();
                    return;
                }
            }

            UserDao users = MyRoomDatabase.getInstance(this).userdao();
            new Thread(() -> {
                int id = users.Login(info[0],info[1]);
                if(id != 0)
                    movetohome(id);
                else
                    runOnUiThread(() -> {
                        Toast. makeText(getApplicationContext(),"Inavalid Username or Password",Toast. LENGTH_SHORT).show();
                    });
            }).start();

        });
        binding.member.setOnClickListener(view->{
            Intent i = new Intent(this, SignupActivity.class);
            startActivity(i);
            finish();
        });

    }



    private void movetohome(int id) {
            Intent i=new Intent(SigninActivity.this, MainActivity.class);
            i.putExtra("id",id);
            startActivity(i);
            finish();
    }
}