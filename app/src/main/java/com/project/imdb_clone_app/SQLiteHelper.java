package com.project.imdb_clone_app;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "imdbclone.db";
    public static final int DB_VER = 1;
    Context context;


    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contract_Actor.CREATE_TABLE);
        db.execSQL(Contract_Movie.CREATE_TABLE);
        db.execSQL(Contract_WatchList.CREATE_TABLE);
        db.execSQL(Contract_Favourite.CREATE_TABLE);
        db.execSQL(Contract_Review.CREATE_TABLE);
        db.execSQL(Contract_User.CREATE_TABLE);
        db.execSQL(Contract_Achievement.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    ///////////////////////////////////////////////////////////////////ACTOR OPERATIONS/////////////////////////////////////////////////////////////////////////

    public void addActor(Model_Actor actor){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Contract_Actor.COLUMN_IMAGE,actor.getImage());
        values.put(Contract_Actor.COLUMN_NAME, actor.getName());
        values.put(Contract_Actor.COLUMN_AGE, actor.getAge());
        values.put(Contract_Actor.COLUMN_DOB, actor.getDateOfBirth());
        values.put(Contract_Actor.COLUMN_MOVIES, actor.getMovie());
        values.put(Contract_Actor.COLUMN_ROLE, actor.getRole());
        values.put(Contract_Actor.COLUMN_DETAIL, actor.getDetail());

        try {
            long i = db.insert(Contract_Actor.TABLE_NAME, null, values);
            if(i > 0){
                Toast.makeText(context , "Entry added successfully", Toast. LENGTH_SHORT).show();
            } else {
                Toast.makeText(context , "Entry already existed", Toast. LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(context , "Error: " + e, Toast. LENGTH_SHORT).show();
        }

        db.close();
    }

    public List<Model_Actor> readActorByMovie(String movieName){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from actor where movies = '"+movieName+"'", null);
        List<Model_Actor> actors = new ArrayList<>();
        while (cursor.moveToNext()) {
            try {
                Model_Actor actor = new Model_Actor();
                actor.setImage(cursor.getInt(1));
                actor.setName(cursor.getString(2));
                actor.setAge(cursor.getInt(3));
                actor.setDateOfBirth(cursor.getString(4));
                actor.setMovie(cursor.getString(5));
                actor.setRole(cursor.getString(6));
                actor.setDetail(cursor.getString(7));

                actors.add(actor);
            } catch (Exception e) {
            }
        }
        return actors;
    }

    ///////////////////////////////////////////////////////////////////ACHIEVEMENTS OPERATIONS/////////////////////////////////////////////////////////////////////////

    public void addAchievement(Model_Achievement achievement){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Contract_Achievement.COLUMN_ACTOR_NAME,achievement.getActor());
        values.put(Contract_Achievement.COLUMN_AWARD, achievement.getAward());
        values.put(Contract_Achievement.COLUMN_YEAR, achievement.getYear());

        try {
            long i = db.insert(Contract_Achievement.TABLE_NAME, null, values);
            if(i > 0){
                Toast.makeText(context , "Entry added successfully", Toast. LENGTH_SHORT).show();
            } else {
                Toast.makeText(context , "Entry already existed", Toast. LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(context , "Error: " + e, Toast. LENGTH_SHORT).show();
        }

        db.close();
    }


    public List<Model_Achievement> readActorAchievements(String actorname){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Contract_Achievement.TABLE_NAME+" where "+Contract_Achievement.COLUMN_ACTOR_NAME+ "='"+actorname+"'", null);
        List<Model_Achievement> achievements = new ArrayList<>();
        while (cursor.moveToNext()) {
            try {
                Model_Achievement achievement = new Model_Achievement();
                achievement.setAward(cursor.getString(2));
                achievement.setYear(cursor.getString(3));
                achievements.add(achievement);
            } catch (Exception e) {
            }
        }
        return achievements;
    }

    ///////////////////////////////////////////////////////////////////REVIEW OPERATIONS/////////////////////////////////////////////////////////////////////////

    public void addReview(Model_Review review){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Contract_Review.COLUMN_USER_NAME, review.getReviewer_name());
        values.put(Contract_Review.COLUMN_MOVIE_NAME, review.getMovie_name());
        values.put(Contract_Review.COLUMN_RATING,review.getRating());
        values.put(Contract_Review.COLUMN_TITLE, review.getTitle());
        values.put(Contract_Review.COLUMN_DESCRIPTION, review.getDescription());

        try {
            long i = db.insert(Contract_Review.TABLE_NAME, null, values);
            if(i > 0){
                Toast.makeText(context , "Entry added successfully", Toast. LENGTH_SHORT).show();
            } else {
                Toast.makeText(context , "You reviewed this movie already!", Toast. LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(context , "Error: " + e, Toast. LENGTH_SHORT).show();
        }
        db.close();
    }

    public List<Model_Review> readReviews(String movieName){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from reviews WHERE movie='"+movieName+"'", null);
        List<Model_Review> reviews = new ArrayList<>();
        while (cursor.moveToNext()) {

            try {
                Model_Review review = new Model_Review();
                review.setReviewer_name(cursor.getString(1));
                review.setMovie_name(cursor.getString(2));
                review.setRating(cursor.getFloat(3));
                review.setTitle(cursor.getString(4));
                review.setDescription(cursor.getString(5));
                reviews.add(review);
            } catch (Exception e) {
            }
        }
        return reviews;
    }

    ///////////////////////////////////////////////////////////////////USER OPERATIONS/////////////////////////////////////////////////////////////////////////

    public boolean addUser(Model_User user){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contract_User.COLUMN_NAME, user.getName());
        values.put(Contract_User.COLUMN_EMAIL,user.getEmail());
        values.put(Contract_User.COLUMN_PASSWORD,user.getPassword());
        try {
            long i = db.insert(Contract_User.TABLE_NAME, null, values);
            if(i > 0){
                Toast.makeText(context , "ACCOUNT CREATED", Toast. LENGTH_SHORT).show();
            } else if(i>0){
                Toast.makeText(context , "Entry already existed", Toast. LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(context , "Error: " + e, Toast. LENGTH_SHORT).show();
        }
        db.close();
        return true;
    }

    public List<Model_User> readAllUsers(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(Contract_User.READ_ALL_TABLE, null);
        List<Model_User> users = new ArrayList<>();
        while (cursor.moveToNext()) {

            try {
                Model_User user = new Model_User();
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));

                users.add(user);
            } catch (Exception e) {
            }
        }
        return users;
    }
    public Model_User getUser(String userEmail){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery(Contract_User.READ_ALL_TABLE+" where email = '"+userEmail+"'", null);
        Model_User user = new Model_User(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        return user;
    }

    ///////////////////////////////////////////////////////////////////FAVOURITES OPERATIONS/////////////////////////////////////////////////////////////////////////


    public void addToFavourites(String username,String movie){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Contract_Favourite.COLUMN_USER_NAME, username);
        values.put(Contract_Favourite.COLUMN_MOVIE_NAME, movie);

        try {
            long i = db.insert(Contract_Favourite.TABLE_NAME, null, values);
            if(i > 0){
                Toast.makeText(context.getApplicationContext(), movie+" added to Favourites", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context , "Entry already existed", Toast. LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(context , "Error: " + e, Toast. LENGTH_SHORT).show();
        }
        db.close();
    }

    public List<Model_Movie> readAllFavourites(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from movies,favourites where favourites.user= '"+MySharedPreferences.getUser(context.getApplicationContext())[0]+"' AND movies.name=favourites.movie", null);
        List<Model_Movie> movieList = new ArrayList<>();

        while (cursor.moveToNext()) {

            try {
                Model_Movie movie = new Model_Movie();
                movie.setImage(cursor.getInt(1));
                movie.setName(cursor.getString(2));
                movie.setYear(cursor.getString(3));
                movie.setDuration(cursor.getString(4));
                movie.setGenre(cursor.getString(5));
                movie.setCast(cursor.getString(6));
                movie.setDirector(cursor.getString(7));
                movie.setWriter(cursor.getString(8));
                movie.setReview(new Model_Review(8, cursor.getString(9), cursor.getString(10),"",""));
                movie.setTrailer(cursor.getString(11));
                movie.setRating(Float.parseFloat(cursor.getString(12)));
                movie.setDescription(cursor.getString(13));

                movieList.add(movie);

            } catch (Exception e) {
            }
        }
        return movieList;
    }

    public boolean findFavourite(String name){
        List<Model_Movie> movies = readAllFavourites();
        for (Model_Movie movie:movies) {
            if(movie.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void removeFromFavourites(String movieName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ Contract_Favourite.TABLE_NAME+
                " WHERE movie='"+movieName+"'");
        db.close();
    }


    ///////////////////////////////////////////////////////////////////MOVIE OPERATIONS/////////////////////////////////////////////////////////////////////////

    public void addMovie(Model_Movie movie){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Contract_Movie.COLUMN_IMAGE,movie.getImage());
        values.put(Contract_Movie.COLUMN_NAME, movie.getName());
        values.put(Contract_Movie.COLUMN_YEAR, movie.getYear());
        values.put(Contract_Movie.COLUMN_DURATION, movie.getDuration());
        values.put(Contract_Movie.COLUMN_GENRE, movie.getGenre());
        values.put(Contract_Movie.COLUMN_CAST, movie.getCast());
        values.put(Contract_Movie.COLUMN_DIRECTOR, movie.getDirector());
        values.put(Contract_Movie.COLUMN_WRITER, movie.getWriter());
        values.put(Contract_Movie.COLUMN_REVIEW_TITLE, movie.getReview().getTitle());
        values.put(Contract_Movie.COLUMN_REVIEW_DESCRIPTION, movie.getReview().getDescription());
        values.put(Contract_Movie.COLUMN_TRAILER_PATH, movie.getTrailer());
        values.put(Contract_Movie.COLUMN_RATING, movie.getRating());
        values.put(Contract_Movie.COLUMN_DESCRIPTION, movie.getDescription());

        try {
            long i = db.insert(Contract_Movie.TABLE_NAME, null, values);
            if(i > 0){
                Toast.makeText(context , "Entry added successfully", Toast. LENGTH_SHORT).show();
            } else {
                Toast.makeText(context , "Entry already existed", Toast. LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(context , "Error: " + e, Toast. LENGTH_SHORT).show();
        }

        db.close();
    }

    public List<Model_Movie> readAllMovies(){
        SQLiteDatabase db = getReadableDatabase();
        List<Model_Movie> movies = new ArrayList<>();
        Cursor cursor = db.rawQuery(Contract_Movie.READ_ALL_TABLE, null);
        while (cursor.moveToNext()) {

            try {
                Model_Movie movie = new Model_Movie();
                movie.setImage(cursor.getInt(1));
                movie.setName(cursor.getString(2));
                movie.setYear(cursor.getString(3));
                movie.setDuration(cursor.getString(4));
                movie.setGenre(cursor.getString(5));
                movie.setCast(cursor.getString(6));
                movie.setDirector(cursor.getString(7));
                movie.setWriter(cursor.getString(8));
                movie.setReview(new Model_Review(8, cursor.getString(9), cursor.getString(10),"",""));
                movie.setTrailer(cursor.getString(11));
                movie.setRating(Float.parseFloat(cursor.getString(12)));
                movie.setDescription(cursor.getString(13));

                movies.add(movie);
            } catch (Exception e) {
            }
        }
        return movies;
    }

    public Model_Movie readMovieById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Model_Movie movieItem=new Model_Movie();
        Cursor cursor = db.rawQuery("select * from movies where _id = "+id, null);
        while (cursor.moveToNext()) {
            try {

                movieItem.setImage(cursor.getInt(1));
                movieItem.setName(cursor.getString(2));
                movieItem.setYear(cursor.getString(3));
                movieItem.setTrailer(cursor.getString(11));
            } catch (Exception e) {
            }
        }
        return movieItem;
    }

    public Model_Movie readMovieByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from movies where name = '"+name+"'", null);
        Model_Movie movie = new Model_Movie();
        while (cursor.moveToNext()) {
            try {
                movie.setImage(cursor.getInt(1));
                movie.setName(cursor.getString(2));
                movie.setYear(cursor.getString(3));
                movie.setDuration(cursor.getString(4));
                movie.setGenre(cursor.getString(5));
                movie.setCast(cursor.getString(6));
                movie.setDirector(cursor.getString(7));
                movie.setWriter(cursor.getString(8));
                movie.setReview(new Model_Review(8, cursor.getString(9), cursor.getString(10),"",""));
                movie.setTrailer(cursor.getString(11));
                movie.setRating(Float.parseFloat(cursor.getString(12)));
                movie.setDescription(cursor.getString(13));
            } catch (Exception e) {
            }
        }
        return movie;
    }

    ///////////////////////////////////////////////////////////////////WATCHLIST OPERATIONS/////////////////////////////////////////////////////////////////////////

    public void addWatchlist(Model_Watchlist watchlist){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract_WatchList.COLUMN_USERNAME, watchlist.getUsername());
        values.put(Contract_WatchList.COLUMN_NAME, watchlist.getName());
        values.put(Contract_WatchList.COLUMN_MOVIES, watchlist.getMovies());
        values.put(Contract_WatchList.COLUMN_DESCRIPTION, watchlist.getDescription());

        try {
            long i = db.insert(Contract_WatchList.TABLE_NAME, null, values);
            if(i > 0){
                Toast.makeText(context , watchlist.getName().toUpperCase()+" : NEW WATCHLIST CREATED", Toast. LENGTH_SHORT).show();
            } else {
                Toast.makeText(context , watchlist.getName().toUpperCase()+" : Watchlist already existed", Toast. LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(context , "Error: " + e, Toast. LENGTH_SHORT).show();
        }
        db.close();
    }

    public List<Model_Watchlist> readAllWatchlists(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Contract_WatchList.TABLE_NAME+" WHERE "+Contract_WatchList.COLUMN_USERNAME+"='"+MySharedPreferences.getUser(context.getApplicationContext())[0]+"'", null);
        List<Model_Watchlist> watchlists= new ArrayList<>();
        while (cursor.moveToNext()) {
            try {
                Model_Watchlist watchlist = new Model_Watchlist();
                watchlist.setUsername(cursor.getString(1));
                watchlist.setName(cursor.getString(2));
                watchlist.setMovies(cursor.getString(3));
                watchlist.setDescription(cursor.getString(4));
                watchlists.add(watchlist);

            } catch (Exception e) {
            }
        }
        return watchlists;
    }

    public void addToWatchlist(Model_Watchlist watchlist, String newMovie){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE "+ Contract_WatchList.TABLE_NAME+
                " SET movies='"+watchlist.getMovies()+","+newMovie+"' "+
                "WHERE name='"+watchlist.getName()+"' AND username='"+watchlist.getUsername()+"'");
        db.close();
    }

    public void removeWatchlist(String watchlistName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+Contract_WatchList.TABLE_NAME+" WHERE "+Contract_WatchList.COLUMN_NAME+"='"+watchlistName+"'");
    }

    public void removeFromWatchlist(Model_Watchlist watchlist, String newMovie){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+ Contract_WatchList.TABLE_NAME+
                " WHERE "+ Contract_WatchList.COLUMN_NAME+"='"+watchlist.getName()+"'");
        db.close();
    }
}
