package example.com.myapplication.Fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import example.com.myapplication.Food;
import example.com.myapplication.MyAdapter;
import example.com.myapplication.MyViewModel;
import example.com.myapplication.R;
import example.com.myapplication.databinding.FragmentShopBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {
    MyViewModel myViewModel;
    FragmentShopBinding fragmentShopBinding;
    MyAdapter myAdapter;


    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_shop, container, false);
        myViewModel =new ViewModelProvider(getActivity(), new SavedStateViewModelFactory(getActivity().getApplication(),this)).get(MyViewModel.class);
        fragmentShopBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_shop,container,false);
        myAdapter = new MyAdapter();
        fragmentShopBinding.recyclerviewshop.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentShopBinding.recyclerviewshop.setAdapter(myAdapter);
        myViewModel.getAllFoodsLive().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                myAdapter.setAllFoods(foods);
                myAdapter.notifyDataSetChanged();
            }
        });
       
        fragmentShopBinding.addshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] name = {
                        "Hello",
                        "World",
                        "Android",
                        "Google",
                        "Studio",
                        "Project",
                        "Database",
                        "Recycler",
                        "View",
                        "String",
                        "Value",
                        "Integer"
                };
                String[] intro = {
                        "你好",
                        "世界",
                        "安卓系统",
                        "谷歌公司",
                        "工作室",
                        "项目\n有味精\n比较辣",
                        "数据库",
                        "回收站",
                        "视图",
                        "字符串",
                        "价值",
                        "整数类型"
                };
                String[] price = {
                        "13",
                        "14.3",
                        "1",
                        "23",
                        "43",
                        "3432",
                        "34",
                        "324",
                        "342",
                        "4324",
                        "342",
                        "432"
                };
                for(int i = 0;i<name.length;i++) {
                    myViewModel.insertFoods(new Food(name[i],intro[i],price[i]));
                }
            }
        });

        fragmentShopBinding.deleteallshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.deleteAllFoods();
            }
        });
        fragmentShopBinding.setLifecycleOwner(getActivity());
        return fragmentShopBinding.getRoot();
    }

}
