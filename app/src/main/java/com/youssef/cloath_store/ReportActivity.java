package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.youssef.cloath_store.databinding.ActivityAddItemBinding;
import com.youssef.cloath_store.databinding.ActivityReportBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.Sales;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;
import com.youssef.cloath_store.roomdatabase.SalesDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            int uid;
            Date date=null;
            if(!binding.date.getText().toString().isEmpty()&&!binding.idu.getText().toString().isEmpty()){
                SimpleDateFormat DMY=new SimpleDateFormat("dd/MM/YYYY");

                try {
                     date= DMY.parse(binding.date.getText().toString()) ;
                } catch (ParseException e) {
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
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
            // aktb logic bta3 data base(insert update delete)
            Sales sale1=new Sales();;
            sale1.setQuantity(20);
            sale1.setDate(Calendar.getInstance());
            sale1.setProductid(30);
            sale1.setUserid(60);
            report.insert(sale1);
            // m7taga tt8yr
            if(id!=0){
                List<Sales> list_sales_id= report.findByIserId(id,date.getTime());
            }
            // m7taga tt8yr
                List<Sales> list_sales_all= report.findByDate(Calendar.getInstance().getTimeInMillis());
                System.out.println("Basdasda");
        }).start();


    }
}