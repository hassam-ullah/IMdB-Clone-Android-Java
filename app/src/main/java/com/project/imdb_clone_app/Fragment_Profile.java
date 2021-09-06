package com.project.imdb_clone_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment_Profile extends Fragment implements ItemClickListener{
    Button add_watchlist_btn;
    TextView toolbar_title;
    Toolbar toolbar;
    View mView;
    RecyclerView watchlistRecyclerView;
    RecyclerView favouritesRecyclerView;

    Adapter_WatchList watchlistAdapter;
    List<Model_Watchlist> watchlists;
    SQLiteHelper helper;
    Adapter_MovieHorizontal movieAdapter;
    List<Model_Movie> movies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mView=view;
        toolbar_title=view.findViewById(R.id.user_toolbar_title);
        toolbar_title.setText(MySharedPreferences.getUser(getContext())[0]);
        toolbar=view.findViewById(R.id.profile_view_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        add_watchlist_btn=view.findViewById(R.id.add_watchlist_button);
        add_watchlist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment_AddWatchlist();
                FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.home_fragment_container, fragment);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        watchlists= helper.readAllWatchlists();
        watchlistRecyclerView =view.findViewById(R.id.watchlist_recycler_view);
        watchlistAdapter =new Adapter_WatchList(watchlists);
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



        favouritesRecyclerView =view.findViewById(R.id.favourites_recycler_view);
        movies= helper.readAllFavourites();
        movieAdapter=new Adapter_MovieHorizontal(movies);
        movieAdapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie_object",movies.get(position));
                Fragment fragment = new Fragment_MovieProfile();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,fragment).addToBackStack(null).commit();
            }
        });
        favouritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        favouritesRecyclerView.setAdapter(movieAdapter);

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        helper=new SQLiteHelper(activity);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_navbar_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,new Fragment_Settings()).addToBackStack(null).commit();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view, int position) {

    }
}
