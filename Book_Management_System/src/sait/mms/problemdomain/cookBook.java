package sait.mms.problemdomain;

/**
 * @author Matt Jones
 * @version 1.1
 * Class cook book extends from book. Has unique publisher and diet attributes
 */
public class cookBook extends book {
        protected String publisher;
        protected char diet;

    public cookBook(long isbn, String callNumber, int available, int total, String title, String publisher, char diet) {
        super(isbn, callNumber, available, total, title);
        this.publisher = publisher;
        this.diet = diet;
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

    public char getDiet(){
        return diet;
    }

    @Override
    public String saveString() {
        return String.format("%d;%s;%d;%d;%s;%s;%s%n", isbn, callNumber, available, total, title, publisher, diet);
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
                        "%s %s %n"
                ,"ISBN:", this.isbn,
                "Call Number:", this.callNumber,
                "Available:", this.available,
                "Total:", this.total,
                "Title:", this.title,
                "Publisher:", this.publisher,
                "Diet:", this.diet);
    }
}
