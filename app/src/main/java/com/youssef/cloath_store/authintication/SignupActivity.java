package com.youssef.cloath_store.authintication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.ActivitySigninBinding;
import com.youssef.cloath_store.databinding.ActivitySignupBinding;
import com.youssef.cloath_store.models.User;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.UserDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    Calendar Date_of_birth = Calendar.getInstance();
    SimpleDateFormat DMY=new SimpleDateFormat("dd-MMM-YYYY");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Date_of_birth.set(Calendar.MINUTE,0);
        Date_of_birth.set(Calendar.SECOND,0);
        Date_of_birth.set(Calendar.MILLISECOND,0);
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        binding.dateofbirth.setOnClickListener(View ->{
            DatePickerDialog datePickerDialog=new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    Date_of_birth.set(Calendar.YEAR,year);
                    Date_of_birth.set(Calendar.MONTH,month);
                    Date_of_birth.set(Calendar.DAY_OF_MONTH,day);
                    binding.DateofBirth.setText(DMY.format(Date_of_birth.getTime()));
                }
            },Date_of_birth.get(Calendar.YEAR),Date_of_birth.get(Calendar.MONTH),Date_of_birth.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.setTitle("Select date");
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();

        });
        binding.member.setOnClickListener(view -> {
            Intent i = new Intent(this,SigninActivity.class);
            startActivity(i);
        });

        binding.appCompatButton.setOnClickListener(view -> {
            String[] info = new String[5];
            info[0] = binding.name.getText().toString();
            info[1] = binding.Email.getText().toString();
            info[2] = binding.password.getText().toString();
            info[3] = binding.phone.getText().toString();
            info[4] = binding.DateofBirth.getText().toString();

            for(int i = 0; i < 5;i++)
            {
                if(info[i] == null || info[i].isEmpty())
                {
                    Toast. makeText(getApplicationContext(),"Please Fill the Requirements",Toast. LENGTH_SHORT).show();
                    return;
                }
            }

            UserDao users = MyRoomDatabase.getInstance(this).userdao();
            new Thread(() -> {
                   User u = new User(info[0],info[1],info[2],info[3],Date_of_birth);
                   users.insert(u);
            }).start();
            finish();
            Intent i = new Intent(this,SigninActivity.class);
            startActivity(i);
        });



    }
}