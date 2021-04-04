package sait.mms.problemdomain;


//0,1 childBook / 2,3 cookBook / 4,5,6,7 paperBook / 8,9 periodicals

/**
* @author Matt Jones
* @version 1.1
* abstract class book is the base class of:
* childBook
* cookBook
* periodicals
* paperBook
*
* provides base constructor and methods
*/

abstract public class book {
 protected long isbn;
 protected String callNumber;
 protected int available;
 protected int total;
 protected String title;

 public book(long isbn, String callNumber, int available, int total, String title){
     this.isbn = isbn;
     this.callNumber = callNumber;
     this.available = available;
     this.total = total;
     this.title = title;
 }


 public abstract long getISBN();

 public abstract String getCallNumber();

 public abstract int getAvailable();

 public abstract int getTotal();

 public abstract String getTitle();

 // periodicals
 public char getFrequency() {
     return ' ';
 }

 //paper books

 public char getGenre(){
     return ' ';
 }

 //child book

 public char getFormat(){
     return ' ';
 }

 //cookbook

 public char getDiet() { return ' ';}
 // all
 public abstract String saveString();

 public abstract void setAvailable(int i);

}
