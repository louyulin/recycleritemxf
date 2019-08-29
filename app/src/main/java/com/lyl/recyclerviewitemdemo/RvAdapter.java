package com.lyl.recyclerviewitemdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

/**
 * Created by louyulin on 2019/8/29.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Holder> {

    Context context;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.title.setText("title: " + position);
        holder.contant.setText("contant: " + position);

        Picasso.with(context)
                .load("https://timgsa.baidu.com/timg?image&quality=80&si" +
                        "ze=b9999_10000&sec=1567069346498&di=5e0627c1d4ca6afe7ed42edfc9" +
                        "3711f2&imgtype=0&src=http%3A%2F%2Fb.h" +
                        "iphotos.baidu.com%2Fimage%2Fpic%2Fi" +
                        "tem%2F908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg").into(holder.iv);

        if (position % 2 == 0){
            Glide.with(context)
                    .load("http://img3.imgtn.bdimg.com/it/u=3583433020,118316633&fm=26&gp=0.jpg")
                    .into(holder.ivc);
        }else {
            Glide.with(context)
                    .load("http://img5.imgtn.bdimg.com/it/u=1043977011,2291928950&fm=26&gp=0.jpg")
                    .into(holder.ivc);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView title, contant;
        ImageView iv;
        ImageView ivc;

        public Holder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            contant = itemView.findViewById(R.id.contant);
            iv = itemView.findViewById(R.id.iv);
            ivc = itemView.findViewById(R.id.ivc);
        }
    }
}
