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

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        TextView title;
        ImageView img;
        ConstraintLayout C;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.category);
            img = itemView.findViewById(R.id.categoryimage);
            C = itemView.findViewById(R.id.categories_rectangle);
        }
    }

    Callback<Categories> callback;
    private List<Categories> categoriesList;
    public CategoryAdapter(Callback<Categories> callback, List<Categories> categoriesList)
    {
        this.callback=callback;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View V =  LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout,parent,false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Categories G = categoriesList.get(position);
        holder.title.setText(G.getTitle());
        Bitmap bmp = BitmapFactory.decodeByteArray(G.getImage(), 0, G.getImage().length);
        holder.img.setImageBitmap(bmp);
        holder.C.setOnClickListener(view -> callback.call(G));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}
