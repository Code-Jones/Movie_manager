package sait.mms.problemdomain;


/**
 * @author Matt Jones
 * @version 1.1
 * Class child book extends from book. Has unique author and format attributes
 */
public class childBook extends book {
    protected String author;
    protected char format;

    public childBook(long isbn, String callNumber, int available, int total, String title, String author, char format) {
       super(isbn, callNumber, available, total, title);
       this.author = author;
       this.format = format;
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

    public char getFormat(){
        return format;
    }

    @Override
    public String saveString() {
        return String.format("%d;%s;%d;%d;%s;%s;%s%n", isbn, callNumber, available, total, title, author, format);
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
                "Author:", this.author,
                "Format:", this.format);
    }

}
