package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.youssef.cloath_store.databinding.ActivityFeedbackandRatingBinding;
import com.youssef.cloath_store.models.OrderFeedback;
import com.youssef.cloath_store.models.ProductFeedback;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.OrderDao;
import com.youssef.cloath_store.roomdatabase.RatingProductDao;

import java.util.Calendar;

public class FeedbackandRatingActivity extends AppCompatActivity {

    int id;
    ActivityFeedbackandRatingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedbackandRatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        id = getIntent().getIntExtra(Constants.idindex,-1);
        binding.button3.setOnClickListener(View -> {

            String temp =  binding.Rating.getText().toString();
            if(temp.isEmpty())
            {
                Toast.makeText(this,"Please Re-enter rating ",Toast.LENGTH_LONG).show();
                return;
            }

            int rating = Integer.parseInt(temp);
            if (rating > 5)
            {
                Toast.makeText(this,"Please Re-enter rating between 1 and 5 ",Toast.LENGTH_LONG).show();
                return;
            }
            String Feedback = binding.Feedback.getText().toString();
            int userid = getIntent().getIntExtra("Uid",-1);
            if(id == -1)
            {
                OrderDao orderDao = MyRoomDatabase.getInstance(this).orderDao();
                new Thread(()->{
                    Calendar C = Calendar.getInstance();
                    C = Constants.return_hour_to_zero(C);
                    OrderFeedback O = new OrderFeedback(userid,C,rating,Feedback);
                    orderDao.insert(O);
                }).start();
            }
            else
            {
                RatingProductDao ratingDao = MyRoomDatabase.getInstance(this).ratingDao();
                new Thread(()->{
                ProductFeedback p = new ProductFeedback(userid,id,rating,Feedback);
                ratingDao.insert(p);
                }).start();
            }
            finish();
        });

    }
}