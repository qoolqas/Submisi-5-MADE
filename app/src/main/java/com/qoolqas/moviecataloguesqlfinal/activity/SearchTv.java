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
import com.qoolqas.moviecataloguesqlfinal.adapter.TvAdapter;
import com.qoolqas.moviecataloguesqlfinal.data.TvShow;
import com.qoolqas.moviecataloguesqlfinal.vm.SearchTvModel;

public class SearchTv extends AppCompatActivity {
    public static final String SEARCH="extra_query";
    private TvAdapter adapter;
    private ProgressBar progressBar;
    private SearchTvModel searchModel;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_fragment);
        String query = getIntent().getStringExtra(SEARCH);

        rv = findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(SearchTv.this));

        progressBar = findViewById(R.id.pb);
        //getdata from viewmodel
        searchModel = ViewModelProviders.of(this).get(SearchTvModel.class);
        searchModel.searchTv(query).observe(this,getTv);
        searchModel.searchTvs(query);

        showLoading(true);
    }
    private Observer<TvShow> getTv = new Observer<TvShow>() {
        @Override
        public void onChanged(@Nullable TvShow tvShow) {
            if (tvShow != null) {
                adapter = new TvAdapter(SearchTv.this,tvShow.getResults());
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
