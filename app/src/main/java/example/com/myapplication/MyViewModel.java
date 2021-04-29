package example.com.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {
    private boolean Display = false;
    SavedStateHandle handle;
    String key = getApplication().getResources().getString(R.string.data_key);
    String shp_name = getApplication().getResources().getString(R.string.shp_name);
    private FoodRepository foodRepository;

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);

        this.handle = handle;
        this.foodRepository = new FoodRepository(application);
        if (!handle.contains(key)) {
            load(key);
        }
    }

    void load(String key) {
        SharedPreferences shp = getApplication().getSharedPreferences(shp_name, Context.MODE_PRIVATE);
        String x = shp.getString(key, "");
        handle.set(key, x);
    }

    public void save(String key, String value) {
        SharedPreferences shp = getApplication().getSharedPreferences(shp_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putString(key, "");
        editor.apply();
    }

    public void setDisplay(boolean display) {
        Display = display;
    }

    public boolean getDisplay() {
        return this.Display;
    }

    public LiveData<List<Food>> getAllFoodsLive() {
        return foodRepository.getAllFoodsLive();
    }

    public void insertFoods(Food... foods) {
        foodRepository.insertFoods(foods);
    }

    public void updateFoods(Food... foods) {
        foodRepository.updateFoods(foods);
    }

    public void deleteFoods(Food... foods) {
        foodRepository.deleteFoods(foods);
    }

    public void deleteAllFoods() {
        foodRepository.deleteAllFoods();
    }
}
