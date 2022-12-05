package com.youssef.cloath_store.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.youssef.cloath_store.R;
import com.youssef.cloath_store.models.Categories;
import com.youssef.cloath_store.models.Products;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView Price;
        ImageView img;
        ConstraintLayout C;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_title);
            Price = itemView.findViewById(R.id.product_price);
            img = itemView.findViewById(R.id.product_image);
            C = itemView.findViewById(R.id.product_rectangle);
        }

    }
    Callback<Products> callback;
    private List<Products> products;
    public ProductAdapter(Callback<Products> callback, List<Products> products)
    {
        this.callback=callback;
        this.products = products;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V =  LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_list,parent,false);
        return new ProductAdapter.ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products G = products.get(position);
        holder.title.setText(G.getTitle());
        holder.Price.setText("Price: " + Integer.toString(G.getPrice()) + "  EGP");
        //Bitmap bmp = BitmapFactory.decodeByteArray(G.getImage(), 0, G.getImage().length);
        //holder.img.setImageBitmap(bmp);
        holder.img.setImageDrawable(holder.img.getContext().getDrawable(R.drawable.jackets));
        holder.C.setOnClickListener(view -> callback.call(G));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}
