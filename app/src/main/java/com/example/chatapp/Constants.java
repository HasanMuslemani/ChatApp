package com.example.chatapp;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.chatapp.Adapters.MessageAdapter;

public class Constants {
    public static void loadProfileImage(Context context, String imageURL, ImageView imageView) {
        if(imageURL.equals("default")) {
            Glide.with(context).load(R.drawable.profile_image_default).into(imageView);
        } else {
            Glide.with(context).load(imageURL).into(imageView);
        }
    }
}
