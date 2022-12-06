package com.youssef.cloath_store.product;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youssef.cloath_store.Controllers.ProductAdapter;
import com.youssef.cloath_store.Product_DescriptionActivity;
import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.FragmentProductBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<Product> products=new ArrayList<>();
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
        getdproudctatabase();
        PAdapter = new ProductAdapter(obj -> {
            Intent i = new Intent(getActivity(), Product_DescriptionActivity.class);
            i.putExtra("id",obj.getUid());
            startActivity(i);
        },products);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(PAdapter);
        return V;
    }

    private void getdproudctatabase() {
        if(type!=null&&!type.isEmpty()){
           ProductDao pdo =MyRoomDatabase.getInstance(getContext()).productDao();
           new Thread(()->{

           }).start();

           new Thread(() -> {
               // for test only
               List<Product> list=pdo.findByCatogy(type);
               //real code
//               List<Product> list=pdo.findByCatogy(type);
               products.addAll(list);
               if(PAdapter!=null)
                     getActivity().runOnUiThread(() -> PAdapter.pushdata(products));
           }).start();
        }
    }
}

