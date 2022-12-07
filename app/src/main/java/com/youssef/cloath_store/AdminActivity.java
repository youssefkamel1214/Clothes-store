package com.youssef.cloath_store;

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
        for(int i=1; i<10;i++ ){
            float value =(float) (i*10);
            //intiallize pie chart
            PieEntry pieEntry= new PieEntry(i,value);
            pieEntries.add(pieEntry);
        }
        PieDataSet pieDataSet=new PieDataSet(pieEntries,"Top 4 Sold");
        //set colors
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //set pie chart data
        pieChart.setData(new PieData(pieDataSet));
        pieChart.animateXY(5000,5000);
        //Hide the describtion of data
        pieChart.getDescription().setEnabled(false);
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