package com.youssef.cloath_store.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youssef.cloath_store.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.category);
            img = itemView.findViewById(R.id.categoryimage);
        }
    }

    private Context context;
    private List<Categories> categoriesList;
    public CategoryAdapter(Context c, List<Categories> categoriesList)
    {
        this.context = c;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View V =  LayoutInflater.from(context).inflate(R.layout.categories_layout,parent,false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Categories G = categoriesList.get(position);
        holder.title.setText(G.getTitle());
        holder.img.setImageDrawable(holder.img.getContext().getDrawable(G.image));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}
