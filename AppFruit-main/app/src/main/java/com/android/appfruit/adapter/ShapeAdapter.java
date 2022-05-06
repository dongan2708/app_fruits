package com.android.appfruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.appfruit.R;
import com.android.appfruit.entity.Fruits;
import com.android.appfruit.entity.Product;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShapeAdapter extends RecyclerView.Adapter
{
    List<Product> userList;
    Context mContext;

    public ShapeAdapter(Context context, List<Product> users)
    {
        this.userList = users;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View shapeView =
                inflater.inflate(R.layout.item_user, parent, false);
        return new ViewHolder(shapeView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product user = userList.get(position);
        ViewHolder myHolder = (ViewHolder) holder;
        myHolder.tv_name.setText(user.getName());
        myHolder.tv_price.setText(String.valueOf(user.getPrice()));
        Glide.with(mContext).load(user.getThumbnail()).into(myHolder.img_user);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View itemview;
        public TextView tv_name;
        public TextView tv_price;
        public ImageView img_user;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemview = itemView;
            this.tv_name = (TextView) itemView.findViewById(R.id.tvName);
            this.tv_price = (TextView) itemView.findViewById(R.id.tvPrice);
            this.img_user = (ImageView) itemView.findViewById(R.id.imgUser);
        }
    }
}
