package com.xhr.inclassapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xhr.inclassapp.databinding.Item57crimeBinding;

import java.util.ArrayList;
import java.util.List;

public class Crime5_7Adapter extends RecyclerView.Adapter<Crime5_7Adapter.ViewHolder> {
    private final List<Crime4_13> crimes;
    private int currentIndex = 0;
    private View.OnClickListener onClickListener;

    public Crime5_7Adapter(List<Crime4_13> crimes) {
        this.crimes = crimes;
    }

    @NonNull
    @Override
    public Crime5_7Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Item57crimeBinding binding = Item57crimeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Crime5_7Adapter.ViewHolder holder, int position) {
        final Crime4_13 crime=crimes.get(position);
        holder.binding.title.setText(crime.getTitle());
        holder.binding.time.setText(crime.getDate().toString());
        holder.itemView.setSelected(currentIndex==position);
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setCurrentIndex(int position) {
        notifyItemChanged(currentIndex);
        notifyItemChanged(position);
        this.currentIndex=position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        Item57crimeBinding binding;

        public ViewHolder(@NonNull Item57crimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemView.setTag(this);
            this.itemView.setOnClickListener(onClickListener);
        }
    }
}
