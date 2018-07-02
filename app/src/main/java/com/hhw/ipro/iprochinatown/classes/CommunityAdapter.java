package com.hhw.ipro.iprochinatown.classes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhw.ipro.iprochinatown.CommuityAboutActivity;
import com.hhw.ipro.iprochinatown.CommunityActivity;
import com.hhw.ipro.iprochinatown.LocationDisplayActivity;
import com.hhw.ipro.iprochinatown.MainActivity;
import com.hhw.ipro.iprochinatown.R;

import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    protected final int chineseAmericanMuseum = 0;
    protected final int puiTakCententer = 4;

    private Context context;
    private List<String> data;
    private int targetType;
    public static final int nextIsList = 0;
    public static final int nextIsDetail = 1;
    public static final int nextIsLocationDisplay = 2;

    public CommunityAdapter(Context context, List<String> data, int targetType){
        this.context = context;
        this.data = data;
        this.targetType = targetType;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_location,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(data.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (targetType){
                    case nextIsDetail:
                        intent = new Intent(context, CommuityAboutActivity.class);
                        intent.putExtra("title_activity", data.get(position));
                        context.startActivity(intent);
                        break;
                    case nextIsList:
                        intent = new Intent(context, CommunityActivity.class);
                        intent.putExtra("title_activity", data.get(position));
                        intent.putExtra("index_community", position);
                        context.startActivity(intent);
                        break;
                    case nextIsLocationDisplay:
                        String[] locationsArr = context.getResources().getStringArray(R.array.locations_array);

                        intent = new Intent(context, LocationDisplayActivity.class);
                        if(data.get(position).equals(locationsArr[chineseAmericanMuseum]))
                            intent.putExtra("nameIndex", chineseAmericanMuseum);
                        else
                            intent.putExtra("nameIndex", puiTakCententer);
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_location_name);
        }
    }
}
