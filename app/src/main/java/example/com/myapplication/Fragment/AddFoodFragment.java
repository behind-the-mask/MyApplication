package example.com.myapplication.Fragment;


import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;

import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import example.com.myapplication.Food;
import example.com.myapplication.MyAdapter;
import example.com.myapplication.MyViewModel;
import example.com.myapplication.R;
import example.com.myapplication.databinding.FragmentAddFoodBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFoodFragment extends Fragment {
    MyViewModel myViewModel;
    FragmentAddFoodBinding fragmentAddFoodBinding;

    public AddFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_food, container, false);
        myViewModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(),this)).get(MyViewModel.class);
        fragmentAddFoodBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_food,container,false);
        fragmentAddFoodBinding.setLifecycleOwner(requireActivity());
        fragmentAddFoodBinding.submit.setEnabled(false);

        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null){
        fragmentAddFoodBinding.foodname.requestFocus();
        imm.showSoftInput(fragmentAddFoodBinding.foodname,0);}

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String foodname = fragmentAddFoodBinding.foodname.getText().toString().trim();
                String foodprice = fragmentAddFoodBinding.foodprice.getText().toString().trim();
                fragmentAddFoodBinding.submit.setEnabled(!foodname.isEmpty() && !foodprice.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        fragmentAddFoodBinding.foodname.addTextChangedListener(textWatcher);
        fragmentAddFoodBinding.foodprice.addTextChangedListener(textWatcher);

        fragmentAddFoodBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodname = fragmentAddFoodBinding.foodname.getText().toString().trim();
                String foodprice = fragmentAddFoodBinding.foodprice.getText().toString().trim();
                String foodintroduction = fragmentAddFoodBinding.foodintroduction.getText().toString().trim();
                Food food = new Food(foodname,foodintroduction,foodprice);
                myViewModel.insertFoods(food);
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.ShopFragment);
            }
        });

        return fragmentAddFoodBinding.getRoot();
    }

}
