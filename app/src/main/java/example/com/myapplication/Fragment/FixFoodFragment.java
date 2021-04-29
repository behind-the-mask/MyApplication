package example.com.myapplication.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import example.com.myapplication.Food;
import example.com.myapplication.MyViewModel;
import example.com.myapplication.R;
import example.com.myapplication.databinding.FragmentFixFoodBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class FixFoodFragment extends Fragment {
    MyViewModel myViewModel;
    FragmentFixFoodBinding fragmentFixFoodBinding;


    public FixFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_fix_food, container, false);
        myViewModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(MyViewModel.class);
        fragmentFixFoodBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_fix_food,container,false);
        fragmentFixFoodBinding.setLifecycleOwner(requireActivity());
        String foodname = getArguments().getString("foodname");
        final int foodid = getArguments().getInt("foodid");
        String foodintroduction = getArguments().getString("foodintroduction");
        String foodprice = getArguments().getString("foodprice");

        fragmentFixFoodBinding.foodprice.setText(foodprice);
        fragmentFixFoodBinding.foodname.setText(foodname);
        fragmentFixFoodBinding.foodintroduction.setText(foodintroduction);
        fragmentFixFoodBinding.textView4.setText(foodid+"修改商品");

        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null){
            fragmentFixFoodBinding.foodname.requestFocus();
            imm.showSoftInput(fragmentFixFoodBinding.foodname,0);}

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String foodname = fragmentFixFoodBinding.foodname.getText().toString().trim();
                String foodprice = fragmentFixFoodBinding.foodprice.getText().toString().trim();
                fragmentFixFoodBinding.submit.setEnabled(!foodname.isEmpty() && !foodprice.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        fragmentFixFoodBinding.foodname.addTextChangedListener(textWatcher);
        fragmentFixFoodBinding.foodprice.addTextChangedListener(textWatcher);

        fragmentFixFoodBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodname = fragmentFixFoodBinding.foodname.getText().toString().trim();
                String foodprice = fragmentFixFoodBinding.foodprice.getText().toString().trim();
                String foodintroduction = fragmentFixFoodBinding.foodintroduction.getText().toString().trim();
                Food food = new Food(foodname,foodintroduction,foodprice);
                food.setId(foodid);
                myViewModel.updateFoods(food);
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.ShopFragment);
            }
        });
        return fragmentFixFoodBinding.getRoot();
    }

}
