package com.youssef.cloath_store.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.ActivityFeedbackReportBinding;
import com.youssef.cloath_store.models.OrderFeedback;
import com.youssef.cloath_store.models.ProductFeedback;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.OrderDao;
import com.youssef.cloath_store.roomdatabase.RatingProductDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FeedbackReportActivity extends AppCompatActivity {
    ActivityFeedbackReportBinding binding;
    OrderDao orderDao;
    RatingProductDao ratingProductDao;
    SimpleDateFormat DMY=new SimpleDateFormat("dd-MMM-YYYY");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFeedbackReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        orderDao= MyRoomDatabase.getInstance(this).orderDao();
        ratingProductDao= MyRoomDatabase.getInstance(this).ratingDao();
        binding.generteorder.setOnClickListener(view -> {
            String userid=binding.userid.getText().toString();
            new Thread(()->{
                ArrayList<OrderFeedback> al;
                if(!userid.isEmpty())
                    al=new ArrayList<>(orderDao.findById( Integer.parseInt(userid)));
                else
                    al=new ArrayList<>(orderDao.Getall());
               ArrayList<String> strings=new ArrayList<>();
               al.forEach(orderFeedback -> {
                   String tmp="Userid :"+ orderFeedback.getUserid()+" Rating : "+orderFeedback.getRate()+" feedback : "+orderFeedback.getFeedback()+" Date : "+ DMY.format(orderFeedback.getDate().getTime());
                   strings.add(tmp);
               });
               runOnUiThread(()->{
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strings);
                binding.listveiw.setAdapter(arrayAdapter);
               });
            }).start();
        });
        binding.generteproduct.setOnClickListener(view -> {
            new Thread(()->{
                ArrayList<ProductFeedback>al;
                String userid=binding.userid.getText().toString();
                if(!userid.isEmpty())
                    al=new ArrayList<>(ratingProductDao.findByUserId(Integer.parseInt(userid)));
                else
                    al=new ArrayList<>(ratingProductDao.getall());
                ArrayList<String> strings=new ArrayList<>();
                al.forEach(productFeedback -> {
                    String tmp="Userid :"+ productFeedback.getUserid()+" Rating : "+productFeedback.getRate()+" feedback : "+productFeedback.getFeedback()+" product id : "+ productFeedback.getProductid();
                    strings.add(tmp);
                });
                runOnUiThread(()->{
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strings);
                    binding.listveiw.setAdapter(arrayAdapter);
                });
            }).start();
        });
    }
}