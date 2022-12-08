package com.youssef.cloath_store.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youssef.cloath_store.databinding.ProductShoppingCardBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.Shoppingcard;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class ProductCardAdapter extends RecyclerView.Adapter<ProductCardAdapter.ViewHolder>{
    List<Shoppingcard>list;
    List<Product>products;
    Callback<List<Shoppingcard>>callback;

    public List<Product> getProducts() {
        return products;
    }

    public ProductCardAdapter(List<Shoppingcard> list, List<Product> products, Callback<List<Shoppingcard>> callback) {
        this.list = list;
        this.products = products;
        this.callback = callback;
    }

    public ProductCardAdapter(List<Shoppingcard> list, List<Product> products) {
        this.list = list;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductShoppingCardBinding binding=ProductShoppingCardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          Shoppingcard shoppingcard=list.get(position);
          Product product=products.get(position);
          holder.binding.title.setText(product.getTitle());
          Bitmap bmp = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
          holder.binding.imageview.setImageBitmap(bmp);
          holder.binding.count.setText(Integer.toString( shoppingcard.getCount()));
          holder.binding.add.setOnClickListener(view ->{
              if(shoppingcard.getCount()==product.getCount())
                  return;
              shoppingcard.setCount(shoppingcard.getCount()+1);
              holder.binding.count.setText(Integer.toString( shoppingcard.getCount()));
              callback.call(list);
          });
          holder.binding.minus.setOnClickListener(view ->{
              shoppingcard.setCount(shoppingcard.getCount()-1);
              holder.binding.count.setText(Integer.toString( shoppingcard.getCount()));
              if(shoppingcard.getCount()==0){
                  list.remove(shoppingcard);
                  products.remove(product);
                  new Thread(()-> MyRoomDatabase.getInstance(holder.binding.add.getContext()).shoppingDao().delete(shoppingcard)).start();
                  notifyDataSetChanged();

              }
              callback.call(list);
          });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void sumbitlist(ArrayList<Product> list) {
        products=list;
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        products.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ProductShoppingCardBinding binding;

        public ViewHolder(ProductShoppingCardBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

    }
}
