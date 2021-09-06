package com.project.imdb_clone_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Model_Watchlist implements Serializable {
    private String username;
    private String name;
    private String description;
    private String movies;

    public Model_Watchlist() {
    }

    public Model_Watchlist(String username, String name, String description, String movies) {
        this.username = username;
        this.name = name;
        this.description = description;
        this.movies = movies;
    }

    protected Model_Watchlist(Parcel in) {
        name = in.readString();
        description = in.readString();
        movies = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
