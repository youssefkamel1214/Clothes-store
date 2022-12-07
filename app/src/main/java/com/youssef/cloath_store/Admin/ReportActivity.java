package com.youssef.cloath_store.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.youssef.cloath_store.Constants;
import com.youssef.cloath_store.databinding.ActivityAddItemBinding;
import com.youssef.cloath_store.databinding.ActivityReportBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.Sales;
import com.youssef.cloath_store.models.User;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;
import com.youssef.cloath_store.roomdatabase.SalesDao;
import com.youssef.cloath_store.roomdatabase.UserDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    //intitialization of global variables

    ActivityReportBinding binding;
    SalesDao report;
    ProductDao proddao;
    UserDao udao;
    ArrayList<Sales> transactions;
    ArrayList<String> trans=new ArrayList<>();
    SimpleDateFormat DMY=new SimpleDateFormat("dd/MM/YYYY");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bind all buttons of this activity
        binding= ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        report= MyRoomDatabase.getInstance(this.getApplicationContext()).salesDao();




        binding.srdate.setOnClickListener(view->{
            Date date=null;
            SimpleDateFormat DMY=new SimpleDateFormat("dd/MM/YYYY");

            try {
                date= DMY.parse(binding.date.getText().toString()) ;
            } catch (ParseException e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
            int uid;

            if(!binding.date.getText().toString().isEmpty()&&!binding.idu.getText().toString().isEmpty()){
                

                uid=Integer.parseInt(binding.idu.getText().toString());

                search(uid,date);

            }
            else if(binding.idu.getText().toString().isEmpty()){
                 uid= 0;
                 search(uid,date);
            }



        });
    }

    public void search(int id, Date date){
        new Thread(()->{
            if(id!=0){
                transactions= new ArrayList<>(report.findByIserId(id,date.getTime()));

            }
            else{
                transactions= new ArrayList<>(report.findByDate(date.getTime()));
            }

            transactions.forEach(sales -> {
                Product product=proddao.findById(sales.getProductid());
                User user=udao.findById(sales.getUserid());
                String uid = " Title: "+product.getTitle()+" Sold on: "+DMY.format(sales.getDate().getTime())+" For: "+user.getName();
                trans.add(uid);

            });
            runOnUiThread(() -> {
                ArrayAdapter<String> listadapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,trans);
                binding.lview.setAdapter(listadapter);

            });
        }).start();


    }
}