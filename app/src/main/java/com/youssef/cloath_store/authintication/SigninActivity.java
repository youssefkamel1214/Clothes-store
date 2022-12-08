package com.youssef.cloath_store.authintication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.youssef.cloath_store.Admin.AdminActivity;
import com.youssef.cloath_store.Constants;
import com.youssef.cloath_store.MainActivity;
import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.ActivitySigninBinding;
import com.youssef.cloath_store.models.Categories;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.roomdatabase.CategoriesDao;
import com.youssef.cloath_store.roomdatabase.MyRoomDatabase;
import com.youssef.cloath_store.roomdatabase.UserDao;

import java.util.ArrayList;
import java.util.Objects;

public class SigninActivity extends AppCompatActivity {
    ActivitySigninBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.actionbar));
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        // check if it first time to open app
        if(sharedPreferences.getBoolean("firsttime", true)){
            putdataforfirsttime();
        }
        // return user id
        if(sharedPreferences.getInt(Constants.RememberValue,-1)!=-1){
            movetohome(sharedPreferences.getInt(Constants.RememberValue,-1));
        }
        binding.signin.setOnClickListener(view -> {
            String [] info = new String[2];
            info[0] = binding.Email.getText().toString();
            info[1] = binding.password.getText().toString();
            for(int i = 0; i < 2;i++)
            {
                if(info[i] == null || info[i].isEmpty())
                {
                    Toast. makeText(getApplicationContext(),"Please Fill the Requirements",Toast. LENGTH_SHORT).show();
                    return;
                }
            }
            if(info[0].equals("admin")&&info[1].equals("admin")){
                Intent intent=new Intent(this, AdminActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            UserDao users = MyRoomDatabase.getInstance(this).userdao();
            new Thread(() -> {
                int id = users.Login(info[0],info[1]);
                if(id != 0) {
                    sharedPreferences.edit().putInt("userid",id).apply();
                    if(binding.rembemer.isChecked()) {
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putInt(Constants.RememberValue, id);
                        myEdit.apply();
                    }

                    movetohome(id);
                }
                else
                    runOnUiThread(() -> {
                        Toast. makeText(getApplicationContext(),"Inavalid Username or Password",Toast. LENGTH_SHORT).show();
                    });
            }).start();

        });
        binding.member.setOnClickListener(view->{
            Intent i = new Intent(this, SignupActivity.class);
            startActivity(i);
            finish();
        });
        binding.ForgetPw.setOnClickListener(view -> {
            Intent i = new Intent(this, ForgetMeActivity.class);
            startActivity(i);
        });

    }



    private void putdataforfirsttime() {
        CreateCategories();
        String []jackets=new String[]{"Casual","Formal Jacket","LeatherJacket","Winter Coat"};
        String []pant=new String[]{"Jeans","Jogger","Sweet Pants","Trousers"};
        String []sheos=new String[]{"boots","casual ","Leather","snickers"};
        String []tshirt=new String[]{"Hoodied","Oversized","Polo","Round"};
        int []jimages=new int[]{R.drawable.aa,R.drawable.formal,R.drawable.leatherjacket,R.drawable.winter_coat};
        int []pimages=new int[]{R.drawable.jeans,R.drawable.jogger,R.drawable.sweetpants,R.drawable.trousers};
        int []simages=new int[]{R.drawable.boots,R.drawable.casualsnicker,R.drawable.leather,R.drawable.snicker};
        int []timages=new int[]{R.drawable.hoodied,R.drawable.oversized,R.drawable.polo,R.drawable.round};
        ArrayList<Product>list=new ArrayList<>();
        for (int i=0;i<4;i++){
            Product product=new Product();
            product.setTitle(jackets[i]);
            product.setPrice(45+2*i);
            product.setCount(12);
            product.setAmountsold(100*i+50);
            product.setCategory("Jackets");
            Bitmap bitmap = ((BitmapDrawable)getDrawable(jimages[i])).getBitmap();
            product.setImage(Constants.getBytes(bitmap));
            list.add(product);
           }
        for (int i=0;i<4;i++){
            Product product=new Product();
            product.setTitle(pant[i]);
            product.setPrice(10+2*i);
            product.setCount(12);
            product.setAmountsold(50*i+5);
            product.setCategory("Pants");
            Bitmap bitmap = ((BitmapDrawable)getDrawable(pimages[i])).getBitmap();
            product.setImage(Constants.getBytes(bitmap));
            list.add(product);
        }
        for (int i=0;i<4;i++){
            Product product=new Product();
            product.setTitle(sheos[i]);
            product.setPrice(10+2*i);
            product.setCount(12);
            product.setAmountsold(2*i+1);
            product.setCategory("Shoes");
            Bitmap bitmap = ((BitmapDrawable)getDrawable(simages[i])).getBitmap();
            product.setImage(Constants.getBytes(bitmap));
            list.add(product);
        }
        for (int i=0;i<4;i++){
            Product product=new Product();
            product.setTitle(tshirt[i]);
            product.setPrice(10+2*i);
            product.setCount(12);
            product.setAmountsold(0);
            product.setCategory("T-Shirts");
            Bitmap bitmap = ((BitmapDrawable)getDrawable(timages[i])).getBitmap();
            product.setImage(Constants.getBytes(bitmap));
            list.add(product);
        }
        new Thread(()->{
            MyRoomDatabase.getInstance(this).productDao().insertll(list);
        }).start();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("firsttime",false).apply();
    }

    private void CreateCategories() {

        String[] Cat = {"T-Shirts", "Pants", "Shoes","Jackets"};
        int[] images = {R.drawable.shirts,R.drawable.pants,R.drawable.wonder,R.drawable.jackets};
        ArrayList<Categories>categories=new ArrayList<>();
        for(int i = 0; i < 4;i++) {
            Bitmap bitmap = ((BitmapDrawable)getDrawable(images[i])).getBitmap();
            byte[] image= Constants.getBytes(bitmap);
            categories.add(new Categories(Cat[i], image));
        }
        CategoriesDao categoriesDao=MyRoomDatabase.getInstance(this).categoriesDao();
        new Thread(()->categoriesDao.insertall(categories)).start();
    }

    private void movetohome(int id) {
            Intent i=new Intent(SigninActivity.this, MainActivity.class);
            i.putExtra("id",id);
            startActivity(i);
            finish();
    }
}