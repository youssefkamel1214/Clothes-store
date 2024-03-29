package com.youssef.cloath_store.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youssef.cloath_store.product.ProductFragment;
import com.youssef.cloath_store.R;
import com.youssef.cloath_store.models.Categories;
import com.youssef.cloath_store.Controllers.CategoryAdapter;
import com.youssef.cloath_store.roomdatabase.CategoriesDao;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter CAdapter;
    private ArrayList<Categories> categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View V = inflater.inflate(R.layout.fragment_home, container, false);
        CategoriesDao categoriesDao= MyRoomDatabase.getInstance(getActivity()).categoriesDao();
        new Thread(() -> {
            categories=new ArrayList<>( categoriesDao.getall());
            getActivity().runOnUiThread(() ->{
                CAdapter = new CategoryAdapter(obj -> movetonewfragmentcatgory(obj), categories);
                recyclerView.setAdapter(CAdapter);
            });
        }).start();
        recyclerView = V.findViewById(R.id.Rv_Categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inflate the layout for this fragment
        return V;
    }

    private void movetonewfragmentcatgory(Categories category) {
        ProductFragment fragment=new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", category.getTitle());
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container_view_tag,fragment).addToBackStack(null).commit();
    }
}