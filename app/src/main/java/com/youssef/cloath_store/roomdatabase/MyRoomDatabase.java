package com.youssef.cloath_store.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.youssef.cloath_store.models.Product;
import com.youssef.cloath_store.models.Rating;
import com.youssef.cloath_store.models.Sales;
import com.youssef.cloath_store.models.Shoppingcard;
import com.youssef.cloath_store.models.User;

@Database(entities = {User.class, Product.class, Sales.class, Rating.class, Shoppingcard.class},version = 1)
@TypeConverters({Converter.class})
public abstract class MyRoomDatabase extends RoomDatabase{

    public abstract UserDao userdao();
    public abstract ProductDao productDao();
    public abstract SalesDao salesDao();
    public abstract RatingDao ratingDao();
    public abstract ShoppingDao shoppingDao();

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
