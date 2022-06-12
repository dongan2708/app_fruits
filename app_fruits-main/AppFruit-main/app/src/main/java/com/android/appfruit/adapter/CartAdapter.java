package com.android.appfruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.appfruit.R;
import com.android.appfruit.entity.Cart;
import com.android.appfruit.entity.CartItem;
import com.android.appfruit.entity.Item;
import com.android.appfruit.entity.Product;
import com.android.appfruit.service.CartService;
import com.android.appfruit.util.RetrofitGenerator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<CartItem> itemList;
    Context mContext;

    public CartAdapter(Context context, List<CartItem> items) {
        this.mContext = context;
        this.itemList = items;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CartItem item = itemList.get(position);
            holder.fruitName.setText(item.getProductName());
            holder.fruitPrice.setText(String.valueOf(item.getUnitPrice()));
            Glide.with(mContext).applyDefaultRequestOptions(new RequestOptions().override(70, 70)).load(item.getThumbnailProduct()).into(holder.imagView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagView;
        public TextView fruitName, fruitPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagView = itemView.findViewById(R.id.imgView1);
            fruitName = itemView.findViewById(R.id.txtView1);
            fruitPrice = itemView.findViewById(R.id.txt11);
        }
    }

}
