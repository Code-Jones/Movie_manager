package sait.mms.problemdomain;

import java.util.Arrays;

/**
 * @author Matt Jones
 * @version 1.1
 * Class paper book extends from book. Has unique author/authors, year and genre attributes
 */

public class paperBook extends book {
    protected String[] authors;
    protected int year;
    protected char genre;

    public paperBook(long isbn, String callNumber, int available, int total, String title, String[] authors, int year, char genre) {
        super(isbn, callNumber, available, total, title);
        this.authors = authors;
        this.year = year;
        this.genre = genre;
    }

    public long getISBN(){
        return isbn;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public int getAvailable() {
        return available;
    }

    public int getTotal() {
        return total;
    }

    public String getTitle() {
        return title;
    }

    public char getGenre(){
        return genre;
    }

    @Override
    public String saveString() {
        return String.format("%d;%s;%d;%d;%s;%s;%d;%s%n", isbn, callNumber, available, total, title, (Arrays.toString(authors)).replace("]", "").replace("[", ""),year, genre);
    }

    @Override
    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString(){
        return String.format(
                "%s %d %n" +
                        "%s %s %n" +
                        "%s %d %n" +
                        "%s %d %n" +
                        "%s %s %n " +
                        "%s %s %n" +
                        "%s %d %n" +
                        "%s %s %n"
                ,"ISBN:", this.isbn,
                "Call Number:", this.callNumber,
                "Available:", this.available,
                "Total:", this.total,
                "Title:", this.title,
                "Author:", Arrays.toString(this.authors).replace("]", "").replace("[", ""),
                "Year:", this.year,
                "Genre:", this.genre);
    }
}
