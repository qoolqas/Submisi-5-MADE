package com.qoolqas.moviecataloguesqlfinal.vm;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qoolqas.moviecataloguesqlfinal.api.Client;
import com.qoolqas.moviecataloguesqlfinal.api.Service;
import com.qoolqas.moviecataloguesqlfinal.data.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieModel extends ViewModel {
    private final static String api = Client.getApiKey();
    private MutableLiveData<Movie> liveData;

    public void searchMovies(String query) {
        Service service = Client.getClient().create(Service.class);
        Call<Movie> movieCall = service.getSearchMovie(api,query);
        Log.d("movie", String.valueOf(service));
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                liveData.postValue(response.body());
//                if (response.body() != null) {
                    Log.d("tag", String.valueOf(response.body()));

                assert response.body() != null;
//                Log.d("callback",response.body().getResults().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("Failed Fetch Data Movie", t.getMessage());
            }
        });
    }
    public LiveData<Movie> searchMovie(String quer){
        if (liveData == null) {
            liveData = new MutableLiveData<>();
//            loadMovies();
            searchMovies(quer);
        }else {
            liveData = new MutableLiveData<>();
//            loadMovies();
            searchMovies(quer);
        }
        return liveData;
    }
}
