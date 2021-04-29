package example.com.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Food> allFoods = new ArrayList<>();
    MyViewModel myViewModel;

    public void setAllFoods(List<Food> allFoods) {
        this.allFoods = allFoods;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.cell_card,parent,false);
        final MyViewHolder holder = new MyViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food food = (Food)holder.itemView.getTag(R.id.word_for_view_holder);
                Toast.makeText(holder.itemView.getContext(),"商品id是："+food.getId(),Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("foodname",food.getFoodname());
                bundle.putInt("foodid",food.getId());
                bundle.putString("foodintroduction",food.getIntroduction());
                bundle.putString("foodprice",food.getPrice());
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_ShopFragment_to_fixFoodFragment,bundle);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Food food = allFoods.get(position);
        holder.itemView.setTag(R.id.word_for_view_holder,food);
        holder.textViewNumber.setText(String.valueOf(position+1));
        holder.textViewName.setText(food.getFoodname());
        holder.textViewIntroduction.setText(food.getIntroduction());
        holder.textViewPrice.setText(food.getPrice());
    }

    @Override
    public int getItemCount() {
        return allFoods.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNumber,textViewName,textViewIntroduction,textViewPrice;
        ImageView back;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewNumber = itemView.findViewById(R.id.textViewNumber);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewIntroduction = itemView.findViewById(R.id.textViewIntroduction);
            this.textViewPrice = itemView.findViewById(R.id.textViewPrice);
            this.back = itemView.findViewById(R.id.back);
        }
    }
}
