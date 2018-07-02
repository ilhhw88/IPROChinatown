package com.hhw.ipro.iprochinatown.classes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhw.ipro.iprochinatown.LocationDisplayActivity;
import com.hhw.ipro.iprochinatown.R;

import java.util.List;

public class LocationDisplayAdapter  extends RecyclerView.Adapter<LocationDisplayAdapter.ViewHolder> {
    private Context context;
    private List<String> data;

    public LocationDisplayAdapter(Context context,List<String> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public LocationDisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocationDisplayAdapter.ViewHolder vh = null;
        //判断viewtype类型返回不同Viewholder
        switch (viewType) {
            case 0:
                vh = new LocationDisplayAdapter.ViewHolderTitle(LayoutInflater.from(context).inflate(R.layout.item_location_display_title, parent,false));
                break;
            case 1:
                vh = new LocationDisplayAdapter.ViewHolderInfo(LayoutInflater.from(context).inflate(R.layout.item_location_display_info, parent,false));
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationDisplayAdapter.ViewHolder holder, final int position) {
        holder.bindHolder(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public int getItemViewType(int position){
        return position%2;
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
        public abstract void bindHolder(String str);
    }

    public class ViewHolderTitle extends LocationDisplayAdapter.ViewHolder{
        TextView title;
        public ViewHolderTitle(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.item_location_display_text_title);
        }

        @Override
        public void bindHolder(String str) {
            title.setText(str);
        }
    }

    public class ViewHolderInfo extends LocationDisplayAdapter.ViewHolder{
        TextView info;
        public ViewHolderInfo(View itemView){
            super(itemView);
            info = itemView.findViewById(R.id.item_location_display_text_info);
        }

        @Override
        public void bindHolder(String str) {
            info.setText(str);
        }
    }
}
