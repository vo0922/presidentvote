package com.example.presidentvote;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private ArrayList<SingerItem> arrayList;
    private Context context;
    private String gname;

    public MyAdapter(ArrayList<SingerItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_managerview_sub, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);
        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_cs.setText("학년 : " + arrayList.get(position).getCs());
        holder.tv_gender.setText("성별 : " + arrayList.get(position).getGender());
        holder.tv_num.setText("투표 수 : " + String.valueOf(arrayList.get(position).getNum()));

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_name;
        TextView tv_cs;
        TextView tv_gender;
        TextView tv_num;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_image);
            this.tv_name = itemView.findViewById(R.id.name);
            this.tv_cs = itemView.findViewById(R.id.cs);
            this.tv_gender = itemView.findViewById(R.id.gender);
            this.tv_num = itemView.findViewById(R.id.num);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), infodelete.class);
                    intent.putExtra("name", tv_name.getText());
                    context.startActivity(intent);
                }
            });
        }
    }
}
