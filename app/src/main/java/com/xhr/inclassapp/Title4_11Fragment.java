package com.xhr.inclassapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.xhr.inclassapp.databinding.FragmentTitle411Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Title4_11Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Title4_11Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "param";

    private FragmentTitle411Binding binding;

    // TODO: Rename and change types of parameters
    private String mParam;

    public Title4_11Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter 1.
     * @return A new instance of fragment Title4_11Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Title4_11Fragment newInstance(String param) {
        Title4_11Fragment fragment = new Title4_11Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentTitle411Binding.inflate(inflater,container,false);

        if(mParam!=null && !mParam.isEmpty()){
            binding.txtTitle.setText(mParam);
        }

        // 2、接受fragment传递的参数
        getParentFragmentManager().setFragmentResultListener("key", this, (requestKey, result) -> {
            String value=result.getString("title");
            binding.txtTitle.setText(value);
        });


        return binding.getRoot();
    }
}