package com.youssef.cloath_store.product;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youssef.cloath_store.Activities.Product_infoActivity;
import com.youssef.cloath_store.Controllers.Callback;
import com.youssef.cloath_store.Controllers.ProductAdapter;
import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.FragmentProductBinding;
import com.youssef.cloath_store.models.Products;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    String type;
    FragmentProductBinding binding;
    private RecyclerView recyclerView;
    private ProductAdapter PAdapter;
    private ArrayList<Products> products;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          type=getArguments().getString("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View V = inflater.inflate(R.layout.fragment_product, container, false);
        recyclerView = V.findViewById(R.id.Rv_Products);
        products = new ArrayList<Products>();

        String[] Cat = {"T-Shirts", "Pants", "Shoes","Jackets"};
        int[] images = {R.drawable.shirts,R.drawable.pants,R.drawable.wonder,R.drawable.jackets};
        products.add(new Products("jacket",1,"shiit",5,2,null));
        products.add(new Products("batats",1,"henaaaa2",5,2,null));
        products.add(new Products("batasss",1,"hena",5,2,null));

        PAdapter = new ProductAdapter(new Callback<Products>() {
            @Override
            public void call(Products obj) {
                Intent i = new Intent(getActivity(), Product_infoActivity.class);
                i.putExtra("id",obj.getId());
                getActivity().startActivity(i);
            }
        },products);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(PAdapter);
        products = new ArrayList<Products>();
        return V;
    }
    }

