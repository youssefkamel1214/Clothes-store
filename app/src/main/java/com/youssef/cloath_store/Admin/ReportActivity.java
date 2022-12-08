package com.youssef.cloath_store.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.youssef.cloath_store.Constants;
import com.youssef.cloath_store.R;
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
    Calendar date=Calendar.getInstance();
    SimpleDateFormat DMY=new SimpleDateFormat("dd/MMM/YYYY");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bind all buttons of this activity
        binding= ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
         date=Constants.return_hour_to_zero(date);
        report= MyRoomDatabase.getInstance(this.getApplicationContext()).salesDao();
        proddao= MyRoomDatabase.getInstance(this.getApplicationContext()).productDao();
        udao= MyRoomDatabase.getInstance(this.getApplicationContext()).userdao();
        binding.dateofbirth.setOnClickListener(view -> createdate());

        binding.srdate.setOnClickListener(view->{
            int uid;
            if(!binding.date.getText().toString().isEmpty()&&!binding.idu.getText().toString().isEmpty()){
                uid=Integer.parseInt(binding.idu.getText().toString());
                search(uid,date);
            }
            else if(!binding.date.getText().toString().isEmpty()&&binding.idu.getText().toString().isEmpty()){
                 uid= 0;
                 search(uid,date);
            }
            else {
                Toast.makeText(this,"please enter the date",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void search(int id, Calendar date){
        new Thread(()->{
            if(id!=0){
                transactions= new ArrayList<>(report.findByIserId(id,date.getTimeInMillis()));
            }
            else{
                transactions= new ArrayList<>(report.findByDate(date.getTimeInMillis()));
            }
            trans.clear();
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
    public void createdate(){
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.set(Calendar.YEAR,year);
                date.set(Calendar.MONTH,month);
                date.set(Calendar.DAY_OF_MONTH,day);
                binding.date.setText(DMY.format(date.getTime()));
            }
        },date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.setTitle("Select date");
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }
}