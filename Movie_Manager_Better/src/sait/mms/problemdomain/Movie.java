package sait.mms.problemdomain;

/**
 * @author Matt Jones
 * @version 1.1
 * Movie class for movies from the Movies database.
 * All setters and getters are included
 */
public class Movie {
    private String title;
    private int year;
    private double duration;

    public Movie(String title, int year, double duration) {
        this.title = title;
        this.year = year;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * ToString for Movie class
     * Nothing special here
     * @return Movie ToString
     */
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                '}';
    }
}
