package com.youssef.cloath_store;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.youssef.cloath_store.databinding.CustomBarDialogBinding;
import com.youssef.cloath_store.databinding.FragmentSearchBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
   FragmentSearchBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSearchBinding.inflate(inflater,container,false);
        binding.searchbarcode.setOnClickListener(view -> {
            barcodedialogbuilder();

        });
        return binding.getRoot();
    }
    private void barcodedialogbuilder() {
        ScanOptions scanOptions=new ScanOptions();
        scanOptions.setPrompt("Volume up to flash on");
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(true);
        scanOptions.setCaptureActivity(CapeActivity.class);
        barLauncher.launch(scanOptions);

    }
    ActivityResultLauncher<ScanOptions>barLauncher=registerForActivityResult(new ScanContract(),result->{
          if(result.getContents()!=null){
              AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
              builder.setTitle("bar code");
              builder.setMessage(result.getContents());
              builder.setPositiveButton("ok", (dialogInterface, i) -> {
                   dialogInterface.dismiss();
              }).show();
          }
    });


}