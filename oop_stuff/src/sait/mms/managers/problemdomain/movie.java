package sait.mms.managers.problemdomain;

/**
 * 
 * @author Matt Jones
 * @version 1.1
 * 
 * Class Movie: Movie class make's movie objects that
 * save the information about the individual movie.
 *
 */
public class movie {
    int time;
    String title;
    int year;

    public movie(int time, String title, int year){
        this.time = time;
        this.title = title;
        this.year = year;
    }

    public int getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
    	return "movie{" +
                "time=" + time +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }

}