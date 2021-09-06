package com.project.imdb_clone_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class Fragment_AddReview extends Fragment {
    EditText review_description,review_title;
    Toolbar toolbar;
    RatingBar ratingBar;
    Button submit_review;
    ImageView mImg;
    TextView mTitle;
    TextView mYear;

    Model_Movie recievedMovieItem;
    SQLiteHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_movie_review, container, false);

        Bundle bundle = getArguments();
        recievedMovieItem = (Model_Movie) bundle.getSerializable("movie_object");
        toolbar=view.findViewById(R.id.movie_review_toolbar);
        toolbar.setTitle(recievedMovieItem.getName());
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        review_title=view.findViewById(R.id.review_title);
        review_description=view.findViewById(R.id.review_description);
        review_description.setMinLines(3);
        mImg=view.findViewById(R.id.r_movie_img);
        mTitle=view.findViewById(R.id.r_movie_title);
        mYear=view.findViewById(R.id.r_movie_year);

        mImg.setImageResource(recievedMovieItem.getImage());
        mTitle.setText(recievedMovieItem.getName());
        mYear.setText(recievedMovieItem.getYear());

        submit_review=view.findViewById(R.id.submit_review);
        ratingBar = view.findViewById(R.id.ratingBar);

        submit_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model_Review review=new Model_Review(ratingBar.getRating(),review_title.getText().toString(),review_description.getText().toString(),MySharedPreferences.getUser(getContext())[0],recievedMovieItem.getName());
                String rating = String.valueOf(ratingBar.getRating());
                Float ratingg= Float.parseFloat(rating);
                helper.addReview(review);
                Toast.makeText(getContext(),rating+" Stars",Toast.LENGTH_SHORT).show();
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
