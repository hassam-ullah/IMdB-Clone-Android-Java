package com.project.imdb_clone_app;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter_Movie extends RecyclerView.Adapter <Adapter_Movie.MovieViewHolder> implements ItemClickListener, Filterable {

    List<Model_Movie> allMovies;
    List<Model_Movie> filteredMovies;
    private ItemClickListener clickListener;
    SQLiteHelper helper;

    public Adapter_Movie(List<Model_Movie> movies){
        this.allMovies = movies;
        this.filteredMovies=movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        MovieViewHolder MovieViewHolder = new MovieViewHolder(view);
        helper = new SQLiteHelper(parent.getContext());
        return MovieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Model_Movie movie = allMovies.get(position);
        holder.img.setImageResource(movie.getImage());
        holder.fav.setImageResource(R.drawable.ic_baseline_thumb_up_alt_white_24);
        holder.name.setText(movie.getName());
        holder.year.setText(movie.getYear());
        holder.rating.setText(Float.toString(movie.getRating()));

        if(helper.findFavourite(movie.getName())){
            holder.fav.setImageResource(R.drawable.ic_baseline_thumb_up_alt_gold_24);
        }

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(helper.findFavourite(movie.getName())){
                    SQLiteHelper helper= new SQLiteHelper(view.getContext());
                    helper.removeFromFavourites(movie.getName());
                    holder.fav.setImageResource(R.drawable.ic_baseline_thumb_up_alt_white_24);
                    Toast.makeText(view.getContext(), "REMOVED FROM FAVOURITE", Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteHelper helper= new SQLiteHelper(view.getContext());
                    helper.addToFavourites(MySharedPreferences.getUser(view.getContext())[0],movie.getName());
                    holder.fav.setImageResource(R.drawable.ic_baseline_thumb_up_alt_gold_24);
                }
            }
        });
    }


    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Model_Movie> listFiltered = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                listFiltered.addAll(allMovies);
            }
            else{
                for(Model_Movie movie:allMovies){
                    String input= charSequence.toString().toLowerCase().trim();
                    if(movie.getName().toLowerCase().contains(input)){
                        listFiltered.add(movie);
                    }
                    if(movie.getYear().contains(input)){
                        listFiltered.add(movie);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=listFiltered;
            return filterResults;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredMovies.clear();
            filteredMovies.addAll((Collection<? extends Model_Movie>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public int getItemCount() {
        return allMovies.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onClick(View view, int position) {

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img;
        ImageView fav;
        TextView name;
        TextView year;
        TextView rating;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.list_movie_image);
            fav=itemView.findViewById(R.id.fav__save_btn);
            name = itemView.findViewById(R.id.list_movie_name);
            year = itemView.findViewById(R.id.list_movie_year);
            rating = itemView.findViewById(R.id.list_movie_rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}

