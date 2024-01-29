package com.sumerge.tmdb.movie.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="adult")
    private boolean adult;

    @Column(name="backdrop_path")
    private String backdropPath;

    @Column(name="original_language")
    private String originalLanguage;

    @Column(name="original_title")
    private String originalTitle;

    @Column(name="overview",length = 3000)
    private String overview;

    @Column(name="popularity")
    private double popularity;

    @Column(name="poster_path")
    private String posterPath;

    @Column(name="release_date")
    private String releaseDate;

    @Column(name="title")
    private String title;

    @Column(name="video")
    private boolean video;

    @Column(name="vote_average")
    private double voteAverage;

    @Column(name="vote_count")
    private int voteCount;

    public Movie() {
    }

    public Movie(boolean adult, String backdropPath, String originalLanguage, String originalTitle, String overview, double popularity, String posterPath, String releaseDate, String title, boolean video, double voteAverage, int voteCount) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", adult=" + adult +
                ", backdropPath='" + backdropPath + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}




