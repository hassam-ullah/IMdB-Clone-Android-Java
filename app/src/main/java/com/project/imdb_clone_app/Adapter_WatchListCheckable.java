package com.project.imdb_clone_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_WatchListCheckable extends RecyclerView.Adapter <Adapter_WatchListCheckable.WatchListCheckableViewHolder> implements ItemClickListener {

    String movieName;
    List<Model_Watchlist> watchlists;
    private ItemClickListener clickListener;
    SQLiteHelper helper;
    public Adapter_WatchListCheckable(List<Model_Watchlist> watchlists, String movieName){
        this.watchlists = watchlists;
        this.movieName=movieName;
    }

    @NonNull
    @Override
    public WatchListCheckableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_checkable_watchlist, parent, false);
        WatchListCheckableViewHolder watchListCheckableViewHolder = new WatchListCheckableViewHolder(view);
        helper= new SQLiteHelper(parent.getContext());
        return watchListCheckableViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WatchListCheckableViewHolder holder, int position) {

        Model_Watchlist watchlist = watchlists.get(position);
        holder.name.setText(watchlist.getName());
        holder.desc.setText(watchlist.getDescription());

        if(watchlist.getMovies().contains(movieName)){
            holder.checkBox.isChecked();
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    if(!watchlist.getMovies().contains(movieName)){
                        Toast.makeText(view.getContext(), movieName.toUpperCase()+" added to "+watchlist.getName(), Toast.LENGTH_LONG).show();
                        helper.addToWatchlist(watchlist,movieName);
                    }
                    else{
                        Toast.makeText(view.getContext(), "Movie Already exists in Watchlist", Toast.LENGTH_LONG).show();
                    }
                }
                else if(!holder.checkBox.isChecked()){
                    if(!watchlist.getMovies().contains(movieName)){
                        Toast.makeText(view.getContext(), movieName.toUpperCase()+" removed from "+watchlist.getName(), Toast.LENGTH_SHORT).show();
                        helper.removeFromWatchlist(watchlist,movieName);
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return watchlists.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    @Override
    public void onClick(View view, int position) {

    }

    public class WatchListCheckableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView desc;
        CheckBox checkBox;

        public WatchListCheckableViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.checkable_wl_n_tv);
            desc = itemView.findViewById(R.id.checkable_wl_d_tv);
            checkBox=itemView.findViewById(R.id.wl_checkbox);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}

