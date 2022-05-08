package com.xhr.inclassapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhr.inclassapp.databinding.FragmentCrimeList58Binding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class CrimeList5_8Fragment extends Fragment {

    private FragmentCrimeList58Binding binding;
    private List<Crime4_13> crimes;
    private Crime5_7Adapter adapter;
    private int currentPos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentCrimeList58Binding.inflate(inflater,container,false);

        initData();

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerview.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        adapter = new Crime5_7Adapter(crimes);

        adapter.setOnClickListener(view -> {
            Crime5_7Adapter.ViewHolder viewHolder = (Crime5_7Adapter.ViewHolder) view.getTag();
            currentPos = viewHolder.getAdapterPosition();
            adapter.setCurrentIndex(currentPos);
            final Bundle bundle = new Bundle();
            bundle.putSerializable("crime", crimes.get(currentPos));
            bundle.putInt("position", currentPos);
            NavHostFragment.findNavController(CrimeList5_8Fragment.this)
                    .navigate(R.id.action_ListFragment_to_CrimeFragment, bundle);
        });

        binding.recyclerview.setAdapter(adapter);

        Bundle arguments=getArguments();
        if (arguments!=null) {
            Crime4_13 crime= (Crime4_13) arguments.getSerializable("IntentCrime");
            int position=arguments.getInt("position");
            crimes.set(position, crime);
            adapter.notifyItemChanged(position);
        }

        return binding.getRoot();
    }

    private void initData() {
        crimes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Crime4_13 crime=new Crime4_13();
            crime.setId(i);
            crime.setTitle("Crime #"+i);
            crime.setDate(new Date());
            crime.setSolved(new Random().nextBoolean());
            crimes.add(crime);
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}