package com.corona.ansh.covid_19.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.corona.ansh.covid_19.Model.DailyTimelineModel;
import com.corona.ansh.covid_19.R;

import androidx.recyclerview.widget.RecyclerView;

public class DailyTimelineAdapter extends  RecyclerView.Adapter<DailyTimelineAdapter.ProductViewHolder> {
    private Context context;
    DailyTimelineModel[] models;

    public DailyTimelineAdapter(Context context, DailyTimelineModel[] apiModel){
        this.context=context;
        this.models =apiModel;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.daily_timeline_layout,parent,false);
        return new ProductViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        final DailyTimelineModel List= models[position];

         holder.date.setText(List.getDate());
         holder.death.setText(List.getTotaldeceased() + "(+" + List.getDailydeceased() + ")");
        holder.recovered.setText(List.getTotalrecovered()+"(+" + List.getDailyrecovered() + ")");
        holder.confirm.setText(List.getTotalconfirmed()+"(+"+List.getDailyconfirmed()+")");

    }

    @Override
    public int getItemCount() {
        try{return models.length;}
        catch (Exception e){

        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView date,death,confirm,recovered;

        LinearLayout linearLayout;
        public ProductViewHolder(View itemView) {
            super(itemView);

            date= itemView.findViewById(R.id.txt_date);
            confirm= itemView.findViewById(R.id.txt_confirmed);
            recovered= itemView.findViewById(R.id.txt_recovered);
            death= itemView.findViewById(R.id.txt_deaths);

            linearLayout =itemView.findViewById(R.id.daily_timeline_items_screen);
        }


    }
}

