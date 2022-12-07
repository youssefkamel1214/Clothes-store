package com.youssef.cloath_store.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.youssef.cloath_store.databinding.ActivityAddItemBinding;
import com.youssef.cloath_store.databinding.ActivityAdminBinding;
import com.youssef.cloath_store.databinding.ActivityReportBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    PieChart pieChart;
    ActivityAdminBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding= ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Pie chart assignment
        pieChart=binding.piChart;
        // array list of vars
        ArrayList<PieEntry> pieEntries=new ArrayList<>();
        //setting the pieEntries (DB)
        ProductDao prod1= MyRoomDatabase.getInstance(this).productDao();
        new Thread(()->{
         int total_sales=prod1.get_toatal_sales();
         ArrayList<Product> top_10 = new ArrayList<>(prod1.get_top_10());
            runOnUiThread(()->{
                //rev
                double amount_prod;
                double others=0;
                for(int i=0; i<=5;i++){
                    amount_prod=top_10.get(i).getAmountsold();
                    PieEntry pieEntry= new PieEntry((float) (amount_prod/total_sales)*100,top_10.get(i).getTitle());
                    others+=amount_prod;
                    pieEntries.add(pieEntry);
                }

                double remaining=total_sales-others;
                PieEntry othersp =new PieEntry(((float)remaining/total_sales)*100,"Others");
                pieEntries.add(othersp);
                PieDataSet pieDataSet=new PieDataSet(pieEntries,"Top N Sold Items");
                //set colors
                pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
                //set pie chart data
                pieChart.setData(new PieData(pieDataSet));
                pieChart.animateXY(5000,5000);
                //Hide the describtion of data
                pieChart.getDescription().setEnabled(false);
            });
        }).start();




            //move to Delete Page
        binding.delete.setOnClickListener(view -> {
            Intent i = new Intent(this,DeleteActivity.class);
            startActivity(i);
            finish();
        });

        //move to Edit Page
        binding.edit.setOnClickListener(view -> {
            Intent i = new Intent(this,EditItemActivity.class);
            startActivity(i);
            finish();
        });

        //move to additem Page
        binding.add.setOnClickListener(view -> {
            Intent i = new Intent(this,AddItemActivity.class);
            startActivity(i);
            finish();
        });

        //move to report Page
        binding.generate.setOnClickListener(view -> {
            Intent i = new Intent(this,ReportActivity.class);
            startActivity(i);
            finish();
        });




    }
}