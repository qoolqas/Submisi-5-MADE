package com.qoolqas.moviecataloguesqlfinal.api;

import com.qoolqas.moviecataloguesqlfinal.data.Movie;
import com.qoolqas.moviecataloguesqlfinal.data.TvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("discover/movie")
    Call<Movie> getMovie(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TvShow> getShow(@Query("api_key") String apiKey);

    @GET ("search/movie")
    Call<Movie> getSearchMovie(@Query("api_key")String apiKey, @Query("query") String query);

    @GET ("search/tv")
    Call<TvShow> getSearchTv(@Query("api_key")String apiKey, @Query("query") String query);

    @GET ("discover/tv")
    Call<Movie> getRelease(@Query("api_key")String apiKey,@Query("primary_release_date.gte") String date,@Query("primary_release_date.lte") String last_date);
}
