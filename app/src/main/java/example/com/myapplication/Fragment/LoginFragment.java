package example.com.myapplication.Fragment;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.prefs.Preferences;

import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import example.com.myapplication.IndexActivity;
import example.com.myapplication.MainActivity;
import example.com.myapplication.MyViewModel;
import example.com.myapplication.R;
import example.com.myapplication.databinding.FragmentLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    MyViewModel myViewModel;
    FragmentLoginBinding binding;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String shp_name = getActivity().getResources().getString(R.string.shp_name);
        SharedPreferences pref = getActivity().getSharedPreferences(shp_name, Context.MODE_PRIVATE);
        myViewModel =new ViewModelProvider(getActivity(), new SavedStateViewModelFactory(getActivity().getApplication(),this)).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        binding.setLifecycleOwner(getActivity());
        boolean isRemember = pref.getBoolean("IsRemember",false);//之前勾了记住密码
        if(isRemember){
            binding.inputPassword.setText(pref.getString("Password",null));
            binding.inputUser.setText(pref.getString("User",null));
            binding.checkBox.setChecked(true);
        }

        binding.bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//bin按钮用于置空输入栏
                binding.inputPassword.setText(null);
                binding.inputUser.setText(null);
            }
        });

        binding.eye.setOnClickListener(new View.OnClickListener() {//控制密码是否可见
            @Override
            public void onClick(View view) {
                if(myViewModel.getDisplay()){
                    binding.inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.inputPassword.setSelection(binding.inputPassword.getText().length());
                    binding.eye.setSelected(false);
                    myViewModel.setDisplay(false);

                }else {
                    binding.inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.inputPassword.setSelection(binding.inputPassword.getText().length());
                    binding.eye.setSelected(true);
                    myViewModel.setDisplay(true);
                }
            }
        });

        binding.loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = binding.inputPassword.getText().toString();
                String user = binding.inputUser.getText().toString();

                if(password.equals("123456") && user.equals("lili")){
                    if(binding.checkBox.isChecked()){//如果勾选了记住密码
                        SharedPreferences shp = getActivity().getSharedPreferences(shp_name, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shp.edit();
                        editor.putString("Password",password);
                        editor.putString("User",user);
                        editor.putBoolean("IsRemember",true);
                        editor.apply();
                    }else {//没有勾
                        SharedPreferences shp = getActivity().getSharedPreferences(shp_name, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shp.edit();
                        editor.putBoolean("IsRemember",false);
                        editor.apply();
                    }
                    Intent intent = new Intent(getActivity(), IndexActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(),"登陆成功",Toast.LENGTH_SHORT).show();
//                    NavController controller = Navigation.findNavController(view);
//                    controller.navigate(R.id.action_loginFragment_to_indexFragment4);
                }else {
                    Toast.makeText(getContext(),"密码或账户名错误，请重新输入",Toast.LENGTH_SHORT).show();
                }

            }
        });


        return binding.getRoot();
    }

}
