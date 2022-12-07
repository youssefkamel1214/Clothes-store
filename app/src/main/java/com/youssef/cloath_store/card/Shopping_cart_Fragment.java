package com.youssef.cloath_store.card;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youssef.cloath_store.Constants;
import com.youssef.cloath_store.Controllers.Callback;
import com.youssef.cloath_store.Controllers.ProductCardAdapter;
import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.FragmentShoppingCartBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.Sales;
import com.youssef.cloath_store.models.Shoppingcard;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;
import com.youssef.cloath_store.roomdatabase.SalesDao;
import com.youssef.cloath_store.roomdatabase.ShoppingDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Shopping_cart_Fragment extends Fragment {

   int userid;
   double total=0;
   ProductCardAdapter productCardAdapter;
   FragmentShoppingCartBinding binding;
    List<Shoppingcard> shoppingcards;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userid= getArguments().getInt("id");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentShoppingCartBinding.inflate(inflater,container,false);
        ProductDao productDao= MyRoomDatabase.getInstance(getContext()).productDao();
        ShoppingDao shoppingDao= MyRoomDatabase.getInstance(getContext()).shoppingDao();
        new Thread(()->{
            shoppingcards=shoppingDao.getbyuserid(userid);
            ArrayList<Product>products=new ArrayList<>();
            shoppingcards.forEach(shoppingcard -> {
                Product product=productDao.findById(shoppingcard.getProudctid());
                total+=product.getPrice()*shoppingcard.getCount();
               products.add(product);
            });
            getActivity().runOnUiThread(() -> {
                productCardAdapter =new ProductCardAdapter(shoppingcards,products,list -> {
                    total=0;
                    shoppingcards=list;
                    List<Product>  tmp=productCardAdapter.getProducts();
                    for (int i=0;i<shoppingcards.size();i++){
                        total+=shoppingcards.get(i).getCount()*tmp.get(i).getPrice();
                    }
                    new Thread(()->shoppingDao.updatell(list)).start();
                    binding.recylercard.setAdapter(productCardAdapter);
                    binding.itemTotal.setText(Double.toString(total)+" Egp");
                    binding.Total.setText(Double.toString(total+10)+" Egp");
                });
                binding.recylercard.setAdapter(productCardAdapter);
                binding.itemTotal.setText(Double.toString(total)+" Egp");
                binding.Total.setText(Double.toString(total+10)+" Egp");
            });
        }).start();
        binding.button.setOnClickListener(view -> {
            if(!shoppingcards.isEmpty()){
                SalesDao salesDao=MyRoomDatabase.getInstance(getContext()).salesDao();
                new Thread(()->{
                    shoppingcards.forEach(shoppingcard -> {
                        Calendar calendar=Calendar.getInstance();
                        calendar=Constants.return_hour_to_zero(calendar);
                        salesDao.insert(new Sales(userid,shoppingcard.getProudctid(),shoppingcard.getCount(),calendar ));

                    });
                    shoppingDao.delete(shoppingcards);
                    shoppingcards.clear();
                    getActivity().runOnUiThread(()->{
                        productCardAdapter.clear();
                        binding.Total.setText("10 Egp");
                        binding.itemTotal.setText("0 Egp");
                    });

                }).start();
            }else {
                Toast.makeText(getContext(),"fadya yabn ws5a", Toast.LENGTH_LONG).show();
            }
        });
        binding.recylercard.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }
}