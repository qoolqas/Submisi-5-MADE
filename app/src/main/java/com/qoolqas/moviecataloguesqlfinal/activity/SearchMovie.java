package com.qoolqas.moviecataloguesqlfinal.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qoolqas.moviecataloguesqlfinal.R;
import com.qoolqas.moviecataloguesqlfinal.adapter.MovieAdapter;
import com.qoolqas.moviecataloguesqlfinal.data.Movie;
import com.qoolqas.moviecataloguesqlfinal.vm.SearchMovieModel;

public class SearchMovie extends AppCompatActivity {
    public static final String SEARCH="extra_query";
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private SearchMovieModel searchModel;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_fragment);
        String query = getIntent().getStringExtra(SEARCH);

        rv = findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(SearchMovie.this));

        progressBar = findViewById(R.id.pb);
        //getdata from viewmodel
        searchModel = ViewModelProviders.of(this).get(SearchMovieModel.class);
        searchModel.searchMovie(query).observe(this,getMovie);
        searchModel.searchMovies(query);

        showLoading(true);
    }
    private Observer<Movie> getMovie = new Observer<Movie>() {
        @Override
        public void onChanged(@Nullable Movie movie) {
            if (movie != null) {
                adapter = new MovieAdapter(SearchMovie.this,movie.getResults());
                rv.setAdapter(adapter);
                showLoading(false);
            }

        }
    };
    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
