package com.jeffreyfhow.itunessearchapp;

import java.util.Objects;

public class Movie {

    private String title;
    private String director;
    private Integer year;
    private String description;
    private String imageUrl;

    public Movie(String title, String director, Integer year, String description, String imageUrl) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public Integer getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(description, movie.description) &&
                Objects.equals(imageUrl, movie.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director, year, description, imageUrl);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
