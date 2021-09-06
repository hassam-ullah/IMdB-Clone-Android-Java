package com.project.imdb_clone_app;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_WatchList extends RecyclerView.Adapter <Adapter_WatchList.WatchListViewHolder> implements ItemClickListener {

    List<Model_Watchlist> watchlists;
    private ItemClickListener clickListener;
    public Adapter_WatchList(List<Model_Watchlist> watchlists){
        this.watchlists = watchlists;
    }
    SQLiteHelper helper;
    Context context;
    @NonNull
    @Override
    public WatchListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_watchlist, parent, false);
        WatchListViewHolder WatchListViewHolder = new WatchListViewHolder(view);
        helper=new SQLiteHelper(view.getContext());
        context= view.getContext();
        return WatchListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WatchListViewHolder holder, int position) {

        Model_Watchlist watchlist = watchlists.get(position);
        holder.name.setText(watchlist.getName());
        holder.desc.setText(watchlist.getDescription());
        /*
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.removeWatchlist(watchlist.getName());
                Toast.makeText(context, "Watchlist Removed! Please Refresh", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

         */
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

    public class WatchListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView desc;
        ImageView img;

        public WatchListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.wl_n_tv);
            desc = itemView.findViewById(R.id.wl_d_tv);
            //img=itemView.findViewById(R.id.wl_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}

