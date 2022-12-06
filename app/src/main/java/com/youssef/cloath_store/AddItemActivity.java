package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.youssef.cloath_store.databinding.ActivityAddItemBinding;
import com.youssef.cloath_store.databinding.ActivityTest2Binding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

public class AddItemActivity extends AppCompatActivity {
    ActivityAddItemBinding binding;
    ProductDao product_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        product_add= MyRoomDatabase.getInstance(this.getApplicationContext()).productDao();
        binding.additem.setOnClickListener(view -> {

            String title=binding.titleadd2.getText().toString();
            String category=binding.categ2.getText().toString();
            float price=Float.parseFloat(binding.price2.getText().toString());
            int amountSold=Integer.parseInt(binding.sold2.getText().toString());
            int count=Integer.parseInt(binding.count2.getText().toString());
            AddItem(title,category,price,amountSold,count);

        });
    }


    public void AddItem(String title,String category,float price,int amountSold,int count) {
        Product Newprod=new Product();
        Newprod.setTitle(title);
        Newprod.setCategory(category);
        Newprod.setPrice(price);
        Newprod.setCount(count);
        Newprod.setAmountsold(amountSold);
        new Thread(()->{
            // (insert New product into the db)
            product_add.insert(Newprod);

            // lw 5lst wm7tag t update ui
            runOnUiThread(() -> {
                Toast.makeText(this,"New item has been added",Toast.LENGTH_SHORT).show();
            });

        }).start();
    }

}