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

public class Student5_2Adapter extends RecyclerView.Adapter<Student5_2Adapter.ViewHolder> {

    private final List<Student5_2> students;
    private final List<Student5_2> studentsAll;
    private int currentIndex = 0;
    private View.OnClickListener onClickListener;

    public Student5_2Adapter(List<Student5_2> students) {
        this.students = students;
        this.studentsAll = new ArrayList<>(students);
    }

    @NonNull
    @Override
    public Student5_2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Item52studentBinding binding = Item52studentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Student5_2Adapter.ViewHolder holder, int position) {
        final Student5_2 student = students.get(position);
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
        }
    }

    public Filter getFilter() {
        return filter;
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Student5_2> filteredList = new ArrayList<>();
            final String kw=charSequence.toString().toLowerCase();

            if (kw.isEmpty()) {
                // 查询字符串为空，则恢复所有数据
                filteredList.addAll(studentsAll);
            }else{
                // 查询字符串不为空，则搜索符合条件的记录
                for (Student5_2 student : students) {
                    if (student.getName().toLowerCase().contains(kw)
                            || student.getClassmate().toLowerCase().contains(kw)
                            || String.valueOf(student.getAge()).contains(kw)) {
                        filteredList.add(student);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }
        
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            students.clear();
            students.addAll((Collection<? extends Student5_2>) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
