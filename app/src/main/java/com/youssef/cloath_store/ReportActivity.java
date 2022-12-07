package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.youssef.cloath_store.databinding.ActivityAddItemBinding;
import com.youssef.cloath_store.databinding.ActivityReportBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.Sales;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;
import com.youssef.cloath_store.roomdatabase.SalesDao;

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
    SimpleDateFormat DMY=new SimpleDateFormat("dd/MM/YYYY");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bind all buttons of this activity
        binding= ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        report= MyRoomDatabase.getInstance(this.getApplicationContext()).salesDao();




        binding.srdate.setOnClickListener(view->{
            search(0, null);
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
//        new Thread(()->{
//            // aktb logic bta3 data base(insert update delete)
//            Sales sale1=new Sales();
//            sale1.setQuantity(20);
//            Date d=null;
//            try {
//               d= DMY.parse("7/12/2022");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            Calendar c=Calendar.getInstance();
//            c.setTimeInMillis(d.getTime());
//            sale1.setDate(c);
//            sale1.setProductid(30);
//            sale1.setUserid(60);
//            report.insert(sale1);
//            // m7taga tt8yr
//            if(id!=0){
//                List<Sales> list_sales_id= report.findByIserId(id,date.getTime());
//            }
//            // m7taga tt8yr
////                List<Sales> list_sales_all= report.findByDate(Calendar.getInstance().getTimeInMillis());
//                System.out.println("Basdasda");
//                System.out.println("Basdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//                System.out.println("Basdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa----------------------------");
//
//            try {
//                System.out.println(report.findByDate(DMY.parse("7/12/2022").getTime()).size());
//                System.out.println(DMY.parse("7/12/2022").getTime());
//                System.out.println(DMY.parse("7/12/2022"));
//                System.out.println(Calendar.getInstance());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }).start();


    }
}