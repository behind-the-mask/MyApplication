package example.com.myapplication;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao   // Database access object
public interface FoodDao {
    @Insert
    void insertFoods(Food... Foods);

    @Update
    void updateFoods(Food... Foods);

    @Delete
    void deleteFoods(Food... Foods);

    @Query("DELETE FROM FOOD")
    void deleteAllFoods();

    @Query("SELECT * FROM FOOD ORDER BY ID DESC")
        //List<Food> getAllFoods();
    LiveData<List<Food>> getAllFoodsLive();

}
