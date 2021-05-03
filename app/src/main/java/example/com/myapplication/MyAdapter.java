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
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends ListAdapter<Food,MyAdapter.MyViewHolder> {
    MyViewModel myViewModel;

    public MyAdapter(MyViewModel myViewModel){
        super(new DiffUtil.ItemCallback<Food>() {
            @Override
            public boolean areItemsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
                return (oldItem.getFoodname().equals(newItem.getFoodname())
                        && oldItem.getPrice().equals(newItem.getPrice())
                        && oldItem.getIntroduction().equals(newItem.getIntroduction()));
            }
        });
        this.myViewModel = myViewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.cell_card,parent,false);
        final MyViewHolder holder = new MyViewHolder(itemView);
        holder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food food = (Food)holder.itemView.getTag(R.id.word_for_view_holder);
                myViewModel.setFixedfood(food);
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_ShopFragment_to_fixFoodFragment);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Food food = getItem(position);
        holder.itemView.setTag(R.id.word_for_view_holder,food);
        holder.textViewNumber.setText(String.valueOf(position+1));
        holder.textViewName.setText(food.getFoodname());
        holder.textViewIntroduction.setText(food.getIntroduction());
        holder.textViewPrice.setText(food.getPrice());
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.textViewNumber.setText(String.valueOf(holder.getAdapterPosition()+1));
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewNumber,textViewName,textViewIntroduction,textViewPrice;
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
