package com.xhr.inclassapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xhr.inclassapp.databinding.Item52studentBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentRoom5_12Adapter extends RecyclerView.Adapter<StudentRoom5_12Adapter.ViewHolder> {

    private final List<Student5_11Entity> students;
    private int currentIndex = 0;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;
    private RoomStudent5_12Activity parentActivity;

    public StudentRoom5_12Adapter(List<Student5_11Entity> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentRoom5_12Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parentActivity= (RoomStudent5_12Activity) parent.getContext();
        Item52studentBinding binding = Item52studentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRoom5_12Adapter.ViewHolder holder, int position) {
        final Student5_11Entity student = students.get(position);
        holder.binding.tvName.setText(student.getName());
        holder.binding.tvClassmate.setText(student.getClassmate());
        holder.binding.tvAge.setText(String.valueOf(student.getAge()));
        holder.itemView.setSelected(currentIndex==position);

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }


    public void setCurrentIndex(int position) {
        notifyItemChanged(currentIndex);
        notifyItemChanged(position);
        this.currentIndex=position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        Item52studentBinding binding;

        public ViewHolder(@NonNull Item52studentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemView.setTag(this);
            this.itemView.setOnClickListener(onClickListener);
            this.itemView.setOnLongClickListener(onLongClickListener);
        }
    }

    public Filter getFilter() {
        return filter;
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            final String kw=charSequence.toString().toLowerCase();

            FilterResults filterResults=new FilterResults();
            filterResults.values=parentActivity.updateStudents(kw);
            return filterResults;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            students.clear();
            students.addAll((Collection<? extends Student5_11Entity>) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
