package com.project.imdb_clone_app;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Video extends Fragment {


    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        toolbar=view.findViewById(R.id.search_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        List<Model_VideoCard> videoCards = new ArrayList<>();
        videoCards.add(new Model_VideoCard(R.drawable.video_t_1, "Celebrity Videos"));
        videoCards.add(new Model_VideoCard(R.drawable.video_t_2, "So Far, IMDb Original Video"));
        videoCards.add(new Model_VideoCard(R.drawable.video_t3, "Casting Call"));
        videoCards.add(new Model_VideoCard(R.drawable.video_t4, "Awards and Events"));
        videoCards.add(new Model_VideoCard(R.drawable.video_t5, "Trailers with Commentary"));
        videoCards.add(new Model_VideoCard(R.drawable.video_t6, "IMDb Exclusive Videos"));
        videoCards.add(new Model_VideoCard(R.drawable.video_t7, "Director's Trademarks"));
        videoCards.add(new Model_VideoCard(R.drawable.video_t8, "Celebrity Watchlists"));

        RecyclerView recyclerView = view.findViewById(R.id.video_recycler_view);

        Adapter_VideoList videoListAdapter = new Adapter_VideoList(videoCards);
        recyclerView.setAdapter(videoListAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }
}