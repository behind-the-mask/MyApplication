package example.com.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

public class FoodRepository {
    private LiveData<List<Food>> allFoodsLive;
    private FoodDao foodDao;

    FoodRepository(Context context) {
        FoodDatabase foodDatabase = FoodDatabase.getDatabase(context.getApplicationContext());
        this.foodDao = foodDatabase.getFoodDao();
        allFoodsLive = foodDao.getAllFoodsLive();
    }

    void insertFoods(Food... foods){
        new InsertAsyncTask(foodDao).execute(foods);
    }

    void updateFoods(Food... foods){
        new UpdateAsyncTask(foodDao).execute(foods);
    }

    void deleteFoods(Food... foods){
        new DeleteAsyncTask(foodDao).execute(foods);
    }

    void deleteAllFoods(){
        new DeleteAllAsyncTask(foodDao).execute();
    }

    public LiveData<List<Food>> getAllFoodsLive() {
        return allFoodsLive;
    }

    public LiveData<List<Food>> findWordsWithPattern(String pattern) {
        return foodDao.findWordsWithPattern("%"+pattern+"%");
    }

    static class InsertAsyncTask extends AsyncTask<Food,Void,Void>{
        private FoodDao foodDao;
        InsertAsyncTask(FoodDao foodDao){
            this.foodDao=foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {
            foodDao.insertFoods(foods);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Food,Void,Void>{
        private FoodDao foodDao;
        UpdateAsyncTask(FoodDao foodDao){
            this.foodDao=foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {
            foodDao.updateFoods(foods);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Food,Void,Void>{
        private FoodDao foodDao;
        DeleteAsyncTask(FoodDao foodDao){
            this.foodDao=foodDao;
        }

        @Override
        protected Void doInBackground(Food... foods) {
            foodDao.deleteFoods(foods);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private FoodDao foodDao;
        DeleteAllAsyncTask(FoodDao foodDao){
            this.foodDao=foodDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodDao.deleteAllFoods();
            return null;
        }
    }
}
