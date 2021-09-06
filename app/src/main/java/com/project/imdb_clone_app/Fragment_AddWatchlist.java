package com.project.imdb_clone_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class Fragment_AddWatchlist extends Fragment {
    EditText watchlist_title;
    EditText watchlist_description;
    Switch switcher;
    Button save;
    Toolbar toolbar;

    SQLiteHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_watchlist, container, false);
        watchlist_title=view.findViewById(R.id.wat_title_tv);
        watchlist_description=view.findViewById(R.id.wat_desc_tv);
        switcher=view.findViewById(R.id.switcher);
        save=view.findViewById(R.id.save_wat_btn);

        toolbar=view.findViewById(R.id.add_watchlist_navbar);
        toolbar.setTitle("Add Watchlist");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model_Watchlist watchlist = new Model_Watchlist();
                watchlist.setUsername(MySharedPreferences.getUser(view.getContext())[0]);
                watchlist.setMovies("");
                watchlist.setName(watchlist_title.getText().toString());
                watchlist.setDescription(watchlist_description.getText().toString());
                helper.addWatchlist(watchlist);
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
