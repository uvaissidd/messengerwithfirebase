package com.example.uvmessenger.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uvmessenger.R;
import com.example.uvmessenger.model.chatList;
import com.example.uvmessenger.view.chats.ChatsActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class chatListAdapter extends RecyclerView.Adapter<chatListAdapter.Holder> {


    private List<chatList> list;
    private Context context;

    public chatListAdapter(List<chatList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_chat_list,parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        chatList chatList  = list.get(position);

        holder.tvName.setText(chatList.getUserName());
        holder.tvDesc.setText(chatList.getDescription());
        holder.tvDate.setText(chatList.getDate());

        if (chatList.getUrlProfile().equals("")){
            holder.profile.setImageResource(R.drawable.imageholder);
        }else {
            Glide.with(context).load(chatList.getUrlProfile()).into(holder.profile);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent( context, ChatsActivity.class).putExtra("userID",chatList.getUserID())
                        .putExtra("userName",chatList.getUserName()).putExtra("userProfile",chatList.getUrlProfile()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDesc,tvDate;
        private CircularImageView profile;
        public Holder(@NonNull View itemView) {
            super(itemView);

            tvDate=itemView.findViewById(R.id.tv_date);
            tvName=itemView.findViewById(R.id.tv_name);
            tvDesc=itemView.findViewById(R.id.tv_desc);
            profile=itemView.findViewById(R.id.image_profile);

        }
    }
}
