package com.youssef.cloath_store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youssef.cloath_store.ViewHolder.Categories;
import com.youssef.cloath_store.ViewHolder.CategoryAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter CAdapter;
    private ArrayList<Categories> categories;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View V = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = V.findViewById(R.id.Rv_Categories);
        categories = new ArrayList<Categories>();

        String[] Cat = {"T-Shirts", "Pants", "Shoes","Jackets"};
        int[] images = {R.drawable.shirts,R.drawable.pants,R.drawable.wonder,R.drawable.jackets};

        for(int i = 0; i < 4;i++)
            categories.add(new Categories(Cat[i],images[i]));

        CAdapter = new CategoryAdapter(getContext(), categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(CAdapter);
        // Inflate the layout for this fragment
        return V;
    }
}