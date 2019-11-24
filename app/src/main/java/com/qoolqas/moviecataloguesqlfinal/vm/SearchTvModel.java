package com.qoolqas.moviecataloguesqlfinal.vm;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qoolqas.moviecataloguesqlfinal.api.Client;
import com.qoolqas.moviecataloguesqlfinal.api.Service;
import com.qoolqas.moviecataloguesqlfinal.data.TvShow;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTvModel extends ViewModel {
    private final static String api = Client.getApiKey();
    private MutableLiveData<TvShow> liveData;

    public void searchTvs(String query) {
        Service service = Client.getClient().create(Service.class);
        Call<TvShow> movieCall = service.getSearchTv(api,query);
        Log.d("tv", String.valueOf(service));
        movieCall.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                liveData.postValue(response.body());
//                if (response.body() != null) {
                    Log.d("tag", String.valueOf(response.body()));

                assert response.body() != null;
//                Log.d("callback",response.body().getResults().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Log.e("Failed", t.getMessage());
            }
        });
    }
    public LiveData<TvShow> searchTv(String quer){
        if (liveData == null) {
            liveData = new MutableLiveData<>();
//            loadMovies();
            searchTvs(quer);
        }else {
            liveData = new MutableLiveData<>();
//            loadMovies();
            searchTvs(quer);
        }
        return liveData;
    }
}
