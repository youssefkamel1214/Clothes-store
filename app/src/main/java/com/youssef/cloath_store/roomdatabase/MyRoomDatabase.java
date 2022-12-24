package com.youssef.cloath_store.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.youssef.cloath_store.models.Categories;
import com.youssef.cloath_store.models.OrderFeedback;
import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.ProductFeedback;
import com.youssef.cloath_store.models.Sales;
import com.youssef.cloath_store.models.Shoppingcard;
import com.youssef.cloath_store.models.User;

@Database(entities = {User.class, Product.class, Sales.class, ProductFeedback.class, Shoppingcard.class, Categories.class,OrderFeedback.class},version = 2)
@TypeConverters({Converter.class})
public abstract class MyRoomDatabase extends RoomDatabase{

    public abstract UserDao userdao();
    public abstract ProductDao productDao();
    public abstract SalesDao salesDao();
    public abstract RatingProductDao ratingDao();
    public abstract ShoppingDao shoppingDao();
    public abstract CategoriesDao categoriesDao();
    public abstract OrderDao orderDao();
    private static volatile MyRoomDatabase INSTANCE;
    public static MyRoomDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (MyRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),MyRoomDatabase.class, "DB_name").build();
                }
            }
        }
        return INSTANCE;
    }

}
