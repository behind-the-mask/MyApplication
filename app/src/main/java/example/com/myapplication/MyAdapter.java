package example.com.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Food> allFoods = new ArrayList<>();

    public void setAllFoods(List<Food> allFoods) {
        this.allFoods = allFoods;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.cell_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Food food = allFoods.get(position);
        holder.textViewNumber.setText(String.valueOf(position+1));
        holder.textViewName.setText(food.getFoodname());
        holder.textViewIntroduction.setText(food.getIntroduction());
        holder.textViewPrice.setText(food.getPrice());
        holder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(),"商品id是："+food.getId(),Toast.LENGTH_SHORT).show();
            }
        });
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
