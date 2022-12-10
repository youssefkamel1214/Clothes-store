package com.youssef.cloath_store.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.youssef.cloath_store.Constants;
import com.youssef.cloath_store.Controllers.ProductAdapter;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.product_description.Product_DescriptionActivity;
import com.youssef.cloath_store.databinding.FragmentSearchBinding;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
   FragmentSearchBinding binding;
    ArrayList<Product> prod_lists;
    ProductAdapter productAdapter;
    ProductDao productDao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSearchBinding.inflate(inflater,container,false);
        productDao= MyRoomDatabase.getInstance(getActivity()).productDao();
        binding.searchbarcode.setOnClickListener(view -> barcodedialogbuilder());
        binding.searchN.setOnClickListener(view -> {
            String s=binding.SearchName.getText().toString().trim()+"%";
            search(s);
        });
        binding.voice.setOnClickListener(view -> {
            Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            startActivityForResult(intent, Constants.voice);
        });
        binding.recler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }

    private void search(String s) {
            new Thread(()->{
                prod_lists= new ArrayList<>(productDao.findByname(s));
                getActivity().runOnUiThread(()->{
                    productAdapter=new ProductAdapter(obj -> {
                        Intent i = new Intent(getActivity(), Product_DescriptionActivity.class);
                        i.putExtra("id",obj.getUid());
                        startActivity(i);
                    },prod_lists);
                    binding.recler.setAdapter(productAdapter);
                });
            }
            ).start();
    }

    private void barcodedialogbuilder() {
        ScanOptions scanOptions=new ScanOptions();
        scanOptions.setPrompt("Volume up to flash on");
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(true);
        scanOptions.setCaptureActivity(CapeActivity.class);
        barLauncher.launch(scanOptions);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.voice&&resultCode==getActivity().RESULT_OK){
            ArrayList<String>text=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            binding.SearchName.setText(text.get(0));
            search(text.get(0));
        }
    }

    ActivityResultLauncher<ScanOptions>barLauncher=registerForActivityResult(new ScanContract(), result->{
          if(result.getContents()!=null){
              /*AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
              builder.setTitle("bar code");
              builder.setMessage(result.getContents());
              builder.setPositiveButton("ok", (dialogInterface, i) -> {
                   dialogInterface.dismiss();
              }).show();*/
              Intent i = new Intent(getActivity(), Product_DescriptionActivity.class);
              i.putExtra("id",Integer.parseInt(result.getContents()));
              startActivity(i);
          }
    });


}