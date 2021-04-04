package sait.mms.problemdomain;


/**
 * @author Matt Jones
 * @version 1.1
 * Class periodicals extends from book. Has unique frequency attribute
 */
public class periodicals extends book {
    protected char frequency;

    public periodicals(long isbn, String callNumber, int available, int total, String title, char frequency) {
        super(isbn, callNumber, available, total, title);
        this.frequency = frequency;
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

    public char getFrequency() {
        return frequency;
    }

    @Override
    public String saveString() {
        return String.format("%d;%s;%d;%d;%s;%s%n", isbn, callNumber, available, total, title, frequency);
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
                        "%s %s %n"
                ,"ISBN:", this.isbn,
                "Call Number:", this.callNumber,
                "Available:", this.available,
                "Total:", this.total,
                "Title:", this.title,
                "Frequency:", this.frequency);
    }
}

