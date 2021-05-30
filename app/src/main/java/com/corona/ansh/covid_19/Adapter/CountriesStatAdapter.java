package com.corona.ansh.covid_19.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.corona.ansh.covid_19.Model.countries_stat;
import com.corona.ansh.covid_19.R;

import androidx.recyclerview.widget.RecyclerView;

public class CountriesStatAdapter extends  RecyclerView.Adapter<CountriesStatAdapter.ProductViewHolder> {
    private Context context;
    countries_stat[] countriesstats;

    public CountriesStatAdapter(Context context, countries_stat[] apiModel){
        this.context=context;
        this.countriesstats =apiModel;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.countries_items_layout,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        final countries_stat List= countriesstats[position];

         holder.country.setText(List.getCountryName());
         holder.total_death.setText(List.getDeaths());
        holder.total_infected.setText(List.getCases());
        holder.total_relif.setText(List.getTotalRecovered());
        holder.total_active.setText(List.getActiveCases());


    }

    @Override
    public int getItemCount() {
        try{return countriesstats.length;}
        catch (Exception e){

        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView country,total_death,total_infected,total_relif,total_active;

        LinearLayout linearLayout;
        public ProductViewHolder(View itemView) {
            super(itemView);

            country= itemView.findViewById(R.id.country);
            total_death= itemView.findViewById(R.id.txt_total_death);
            total_infected= itemView.findViewById(R.id.txt_total_infected);
            total_relif= itemView.findViewById(R.id.txt_relif);
            total_active = itemView.findViewById(R.id.total_active);

            linearLayout =itemView.findViewById(R.id.countries_items_screen);
        }


    }
}

