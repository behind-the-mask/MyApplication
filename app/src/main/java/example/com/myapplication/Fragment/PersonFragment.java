package example.com.myapplication.Fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import example.com.myapplication.MyViewModel;
import example.com.myapplication.R;
import example.com.myapplication.databinding.FragmentPersonBinding;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {
    MyViewModel myViewModel;
    FragmentPersonBinding binding;

    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myViewModel =new ViewModelProvider(getActivity(), new SavedStateViewModelFactory(getActivity().getApplication(),this)).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_person,container,false);
        binding.setLifecycleOwner(getActivity());
        binding.privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_PersonFragment_to_settingFragment);
            }
        });
        return binding.getRoot();
    }

}
