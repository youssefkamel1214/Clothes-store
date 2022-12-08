package com.youssef.cloath_store.Admin;

import static com.youssef.cloath_store.R.id.id_delete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.ActivitySigninBinding;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.ProductDao;

public class DeleteActivity extends AppCompatActivity {
    EditText id_del;
    Button Delete;
    ProductDao product_del;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        setContentView(R.layout.activity_delete);
        id_del=findViewById(R.id.id_delete);
        Delete=findViewById(R.id.del);
        byte arr[]={};
        product_del= MyRoomDatabase.getInstance(this.getApplicationContext()).productDao();


        Delete.setOnClickListener(view -> {
            int id_entered=Integer.parseInt(id_del.getText().toString());
            delete(id_entered);

        });

    }

    public void delete(int id){
        new Thread(()->{
            // aktb logic bta3 data base(insert update delete)
            Product prod1= product_del.findById(id);
            product_del.delete(prod1);
            // lw 5lst wm7tag t update ui
            runOnUiThread(() -> {
                Toast.makeText(this,"This Item has beem Removed",Toast.LENGTH_SHORT).show();
                id_del.setText(" ");
                finish();
            });

        }).start();

    }
}