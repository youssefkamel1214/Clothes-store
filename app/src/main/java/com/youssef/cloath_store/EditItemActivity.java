package com.youssef.cloath_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.youssef.cloath_store.databinding.ActivityEditItemBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.Products;
import com.youssef.cloath_store.roomdatabase.ProductDao;
import com.youssef.cloath_store.roomdatabase.SalesDao;

public class EditItemActivity extends AppCompatActivity {

    ActivityEditItemBinding binding;
    Product prodedit;
    ProductDao prodeditdao;

    String Category;
    String title;
    int count;
    float price;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding= ActivityEditItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sedit.setOnClickListener(view -> {
            //get id then get object with id
            id=Integer.parseInt(binding.idedit.getText().toString());
            prodedit = prodeditdao.findById(id);
            //set category
            Category=prodedit.getCategory();
            binding.catedit.setText(Category);
            //set view count
            count=prodedit.getCount();
            binding.countedit.setText(count);
            //set title
            title=prodedit.getTitle();
            binding.titleedit.setText(title);
            //set view price
            price=prodedit.getPrice();
            binding.priceedit.setText(String.valueOf(price));

        });

        binding.edit1.setOnClickListener(view->{

            editObj();
            Toast.makeText(this,"This item has been updated",Toast.LENGTH_SHORT).show();

        });

    }

    //Edit object
    public void editObj(){
        Category=binding.catedit.getText().toString();
        count=Integer.parseInt(binding.countedit.getText().toString());
        title=binding.titleedit.getText().toString();
        price=Float.parseFloat(binding.priceedit.getText().toString());
        //set the new values too the obj
        prodedit.setCount(count);
        prodedit.setTitle(title);
        prodedit.setPrice(price);
        prodedit.setCategory(Category);
        prodeditdao.updateProduct(prodedit);
    }

}