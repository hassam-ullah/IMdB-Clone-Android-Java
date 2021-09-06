package com.project.imdb_clone_app;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment_WatchlistMovieList extends Fragment {
    Toolbar toolbar;
    TextView toolbar_title;

    SQLiteHelper helper;
    Adapter_MovieSimple movieAdapter;
    List<Model_Movie> movies;
    RecyclerView recyclerView;
    Model_Watchlist recievedWatchlistItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watchlist_movie_list_view, container, false);

        Bundle bundle=this.getArguments();
        recievedWatchlistItem= (Model_Watchlist) bundle.getSerializable("watchlist_object");

        toolbar_title=view.findViewById(R.id.watchlist_view_title);
        toolbar_title.setText(recievedWatchlistItem.getName());
        toolbar=view.findViewById(R.id.watchlist_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);


        recyclerView=view.findViewById(R.id.watchlist_movies_recycler_view);

        movies= new ArrayList<>();
        List<String> moviesStr;
        String moviesString = recievedWatchlistItem.getMovies();
        String[] elements = moviesString.split(",");
        List<String> fixedLenghtList = Arrays.asList(elements);
        moviesStr = new ArrayList<String>(fixedLenghtList);

        if(moviesStr.isEmpty()){
            return view;
        }
        for (String str:moviesStr) {
            Model_Movie movie = helper.readMovieByName(str);
            movies.add(movie);
        }
        movieAdapter=new Adapter_MovieSimple(movies);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(movieAdapter);

        return view;
    }
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        helper=new SQLiteHelper(activity);
    }
}
