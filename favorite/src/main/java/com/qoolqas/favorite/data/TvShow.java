package com.qoolqas.favorite.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.qoolqas.favorite.database.FavoriteContract;

import java.util.List;

import static com.qoolqas.favorite.database.FavoriteContract.getColumn;
import static com.qoolqas.favorite.database.FavoriteContract.getColumnDouble;
import static com.qoolqas.favorite.database.FavoriteContract.getColumnInt;

public class TvShow implements Parcelable {
    public TvShow(Cursor cursor) {
        this.id = getColumnInt(cursor, FavoriteContract.TvColumns._ID);
        this.title = getColumn(cursor, FavoriteContract.TvColumns.TITLE_TV);
        this.overview = getColumn(cursor, FavoriteContract.TvColumns.OVERVIEW_TV);
        this.releaseDate = getColumn(cursor, FavoriteContract.TvColumns.RELEASE_TV);
        this.voteAverage = getColumnDouble(cursor, FavoriteContract.TvColumns.USER_RATING_TV);
        this.posterPath = getColumn(cursor, FavoriteContract.TvColumns.POSTER_PATH_TV);
    }
    @SerializedName("id")
    private Integer id;

    public TvShow(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("name")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("first_air_date")
    private String releaseDate;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("results")
    private List<TvShow> results;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public List<TvShow> getResults() {
        return results;
    }

    public void setResults(List<TvShow> results) {
        this.results = results;
    }

    public TvShow() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.posterPath);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.voteAverage);
        dest.writeTypedList(this.results);
    }

    private TvShow(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.posterPath = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.results = in.createTypedArrayList(TvShow.CREATOR);
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
