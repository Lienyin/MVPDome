package com.example.mvpdome.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SetingAdapter extends RecyclerView.Adapter<SetingAdapter.RecyclerHolder>{


    @NonNull
    @Override
    public SetingAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SetingAdapter.RecyclerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder{
        public RecyclerHolder(View view) {
            super(view);
        }
    }
}
