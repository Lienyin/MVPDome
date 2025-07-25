package com.example.mvpdome.ui.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvpdome.R;
import com.example.mvpdome.bean.WallPaperResponse;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<WallPaperResponse.ResBean.VerticalBean> vertical = new ArrayList<>();

    public UserAdapter(Context mContext){
        this.mContext = mContext;
    }

    public void setData(List<WallPaperResponse.ResBean.VerticalBean> vertical) {
        this.vertical = vertical;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(mContext).load(vertical.get( position).getImg()).into(holder.iv_icon);

        holder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(vertical.get(position).getImg());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return vertical.size() == 0 ? 0 : vertical.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_icon;
        public ViewHolder(View view) {
            super(view);
            iv_icon = view.findViewById(R.id.iv_icon);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String url);
    }
    public OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
