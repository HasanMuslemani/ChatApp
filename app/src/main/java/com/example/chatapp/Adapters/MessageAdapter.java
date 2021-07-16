package com.example.chatapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chatapp.Constants;
import com.example.chatapp.Models.Message;
import com.example.chatapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter{

    public static final int LEFT_MESSAGE = 0;
    public static final int RIGHT_MESSAGE = 1;

    private Context context;
    private List<Message> messages;
    private String imageURL;

    FirebaseUser firebaseUser;

    public MessageAdapter(Context context, List<Message> messages, String imageURL) {
        this.context = context;
        this.messages = messages;
        this.imageURL = imageURL;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if(viewType == RIGHT_MESSAGE) {
            view  = LayoutInflater.from(context).inflate(R.layout.chatbox_item_right, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.chatbox_item_left, parent, false);
        }
        return new MessageAdapter.MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        ((MessageViewHolder) holder).messageText.setText(message.getMessage());

        Constants.loadProfileImage(context, imageURL, ((MessageViewHolder) holder).profileImage);


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(messages.get(position).getSenderID().equals(firebaseUser.getUid())) {
            return RIGHT_MESSAGE;
        } else {
            return LEFT_MESSAGE;
        }
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public ImageView profileImage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.messageText);
            profileImage = itemView.findViewById(R.id.profileImage);
        }
    }
}
