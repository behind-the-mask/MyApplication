package example.com.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//singleton
@Database(entities = {Food.class},version = 1,exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {
    private static FoodDatabase INSTANCE;
    static synchronized FoodDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),FoodDatabase.class,"food_database").build();
        }
        return INSTANCE;
    }
    public abstract FoodDao getFoodDao();
}
