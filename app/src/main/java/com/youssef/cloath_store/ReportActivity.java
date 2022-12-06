package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.youssef.cloath_store.databinding.ActivityAddItemBinding;
import com.youssef.cloath_store.databinding.ActivityReportBinding;
import com.youssef.cloath_store.models.Sales;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;
import com.youssef.cloath_store.roomdatabase.SalesDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    ActivityReportBinding binding;
    SalesDao report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        report= MyRoomDatabase.getInstance(this.getApplicationContext()).salesDao();
        binding.srdate.setOnClickListener(view->{

            if(!binding.date.getText().toString().isEmpty()){
                SimpleDateFormat DMY=new SimpleDateFormat("dd-MMM-YYYY");

                try {
                    Date date= DMY.parse(binding.date.getText().toString()) ;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            if(!binding.idu.getText().toString().isEmpty()){
                int uid=Integer.parseInt(binding.idu.getText().toString());
            }
            else if(binding.idu.getText().toString().isEmpty()){
                int uid= Integer.parseInt(null);
            }



        });
    }

    public void search(int id, Date date){

        if(id!=Integer.parseInt(null)){

            List<Sales> list_sales_id= report.findByIserId(id,date.getTime());

        }
        else if(id==Integer.parseInt(null)){
            List<Sales> list_sales_all= report.findByDate(date.getTime());
        }

    }
}