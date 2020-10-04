package com.example.tripit.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripit.Interface.ShopClickListner;
import com.example.tripit.R;

public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtShopItemName , txtShopItemPrice, txtShopItemDescription, txtShopItemQuantity,txtShopItemNumber;
    public ImageView ShopImageView;
    public ShopClickListner shoplistner;

    public ShopViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtShopItemName= (TextView)itemView.findViewById(R.id.item_nam_shop);
        txtShopItemPrice = (TextView)itemView.findViewById(R.id.Item_price_shop);
        txtShopItemDescription= (TextView)itemView.findViewById(R.id.Item_Description_shop);
        txtShopItemNumber= (TextView)itemView.findViewById(R.id.Item_number_shop);

        ShopImageView= (ImageView)itemView.findViewById(R.id.my_shop_image);

    }

    public void setShopClickListner(ShopClickListner listner)
    {

        this.shoplistner = listner;

    }

    @Override
    public void onClick(View v)
    {

    }
}
