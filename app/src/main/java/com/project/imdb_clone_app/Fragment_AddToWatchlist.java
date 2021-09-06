package com.project.imdb_clone_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment_AddToWatchlist extends Fragment {

    Toolbar toolbar;
    RecyclerView watchlistRecyclerView;
    Adapter_WatchListCheckable watchlistAdapter;
    Button button;
    Model_Movie recievedMovieItem;

    List<Model_Watchlist> watchlists;
    SQLiteHelper helper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_to_watchlist, container, false);
        Bundle bundle=this.getArguments();
        recievedMovieItem= (Model_Movie) bundle.getSerializable("movie_object");

        button=view.findViewById(R.id.atw_btn);

        toolbar=view.findViewById(R.id.atw_toolbar);
        toolbar.setTitle("Add to Watchlist");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        watchlists= helper.readAllWatchlists();
        watchlistRecyclerView =view.findViewById(R.id.atw_watchlist_recycler_view);
        watchlistAdapter =new Adapter_WatchListCheckable(watchlists,recievedMovieItem.getName());
        watchlistAdapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("watchlist_object",watchlists.get(position));
                Fragment fragment = new Fragment_WatchlistMovieList();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,fragment).addToBackStack(null).commit();
            }
        });
        watchlistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        watchlistRecyclerView.setAdapter(watchlistAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();
                bundle.putSerializable("movie_object",recievedMovieItem);
                Fragment fragment = new Fragment_MovieProfile();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,fragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        helper=new SQLiteHelper(activity);
    }
}
