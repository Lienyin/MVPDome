package com.example.mvpdome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpdome.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.RecyclerHolder> {

    private Context mContext;
    private ArrayList<String> dataList = new ArrayList<>();

    public UserAdapter(Context mContext){
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.id_rv_item_layout, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.textView.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size() == 0 ? 0 : dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public RecyclerHolder(View view){
            super(view);
            textView = (TextView) itemView.findViewById(R.id.tv_id_item_layout);
        }
    }

    public class UserData<T>{
        public ArrayList<T> dataList;
    }
}
