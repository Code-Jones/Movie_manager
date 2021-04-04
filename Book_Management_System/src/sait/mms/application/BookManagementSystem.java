package sait.mms.application;

import sait.mms.problemdomain.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Matt Jones
 * @version Jan 29, 2020
 * this is the all the methods for the management system
 */

public class BookManagementSystem {

    protected Scanner keyBoard;
    protected ArrayList<book> books;
    protected String filePath;

    public BookManagementSystem(Scanner keyBoard, ArrayList<book> books, String filePath){
        this.keyBoard = keyBoard;
        this.books = books;
        this.filePath = filePath;
    }

    /**
     * checks in a book by the isbn code. checks to make sure its has the correct input.
     * if current record says that all books are checked in it will give you a message saying
     * so and allow you to correct your choice.
     */
    public void checkIn(){
        System.out.println("Enter the ISBN of the book you wish to check in: ");
        while(!keyBoard.hasNextLong()) {
            keyBoard.next();
            System.out.println("Enter the ISBN of the book you wish to check in: ");
        }
        long inputISBN = keyBoard.nextLong();
        for (book bb:books) {
            if(bb.getISBN() == inputISBN && bb.getAvailable() < bb.getTotal()){
                bb.setAvailable(bb.getAvailable() + 1);
                System.out.println("Book is checked in. Thank you");
            }
            else if (bb.getISBN() == inputISBN && bb.getAvailable() >= bb.getTotal()) {
                System.out.printf("Are you sure this is the correct ISBN? %d " +
                        "all of the available books are in the library already.%n", inputISBN);
            }
        }
    } //done


    /**
     * allows users to find a book by the type category.
     * Has input checking to check for correct input choice.
     * prints out a list of books of the selected category
     */
    public void showByType() {
        System.out.print(
                        "#\tType\n" +
                        "1\tChildren's Books\n" +
                        "2\tCookbooks\n" +
                        "3\tPaperbacks\n" +
                        "4\tPeriodicals\n" +
                        "Enter type of book: ");
        // checking for correct input
        boolean checking = true;
        int checkInput = 0;
        while(checking) {
            try {
                checkInput = keyBoard.nextInt();
                if (checkInput> 4 || checkInput <= 0){
                    System.out.println("try again Ali");
                    checkInput = 0;
                    continue;
                }
                checking = false;
            } catch (Exception e) {
                keyBoard.nextLine();
                System.out.println("try again Ali");
            }
        }
        switch (checkInput) {
            case 1:
                System.out.print("P for Picture book, E for Early Readers, or C for chapter book: ");
                char child = keyBoard.next().toUpperCase().charAt(0);
                for (book bb:books) {
                    if (bb instanceof childBook && bb.getFormat() == child) {
                        System.out.println(bb.toString());
                    }
                }
                break;
            case 2:
                System.out.print("D for Diabetic, V for Vegetarian, G for Gluten-free, I for International, N for None: ");
                char cook = keyBoard.next().toUpperCase().charAt(0);
                for (book bb:books) {
                    if (bb instanceof cookBook && bb.getDiet() == cook) {
                        System.out.println(bb.toString());
                    }
                }
                break;
            case 3:
                System.out.print("A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy, S for Science Fiction: ");
                char paper = keyBoard.next().toUpperCase().charAt(0);
                for (book bb:books) {
                    if (bb instanceof paperBook && bb.getGenre() == paper) {
                        System.out.println(bb.toString());
                    }
                }
                break;
            case 4:
                System.out.print("D for Daily, W for Weekly, M for Monthly, B for Biweekly, or Q for Quarterly: ");
                char per = keyBoard.next().toUpperCase().charAt(0);
                for (book bb:books) {
                    if (bb instanceof periodicals && bb.getFrequency() == per) {
                        System.out.println(bb.toString());
                    }
                }
                break;
            default:
                System.out.println("Bad input");
        }
    } //done

    /**
     * Allows users to check by title to find a book.
     * a call number is returned to them after to allow them to find the book
     */
    public void searchTitle() {
        boolean printed = true;
        System.out.print("Enter title to search for: ");
        String input = keyBoard.next();
        for (book bb:books) {
            if (bb.getTitle().toLowerCase().contains(input.toLowerCase())) { // not great but meh
                if(printed) {
                    System.out.println("Matching Books: ");
                    printed = false;
                }
                System.out.println(bb.toString());
            }
        }
    } // done

    /**
     * allows users to check out a book by isbn number.
     * checks to make sure it's a valid input and then for
     * book availability. if a periodical isbn is ran the
     * program returns a message telling them they cannot
     * sign them out. when a book is checked out the
     * availability is updated before finishing the function.
     */
    public void checkoutBook() {
        // periodicals cannot be checked out 8,9
        System.out.print("Enter ISBN of book: ");
        while(!keyBoard.hasNextLong()) {
            keyBoard.next();
            System.out.print("Enter ISBN of book: ");
        }
        long inputISBN = keyBoard.nextLong();
        for (book bb:books) {
            if(bb.getISBN() == inputISBN) {
                if (bb instanceof periodicals) {
                    System.out.println("Periodicals cannot be signed out");
                } else if (bb.getAvailable() != 0) {
                    System.out.printf(
                            "The book \"%s\" has been checked out.%n" +
                            "It can be located using a call number: %s %n",
                            bb.getTitle(), bb.getCallNumber());
                    bb.setAvailable(bb.getAvailable() - 1);
                } else {
                    System.out.printf("\"The book \" %s is not available at this moment.%n",
                            bb.getTitle());
                }
            }
        }
    } // done

    /**
     * Produces a random list of books for the user based of their input
     * of how many books they want to be shown. Checks users input for
     * correct data type
     */
    public void randomList() {
        Random ran = new Random();
        int num;
        do {
            System.out.print("Enter number of books: ");
            while(!keyBoard.hasNextInt()) {
                keyBoard.next();
                System.out.println("Enter number of books: ");
            }
            num = keyBoard.nextInt();
            if (num > books.size()) {
                System.out.println("That's far to many, try again.");
            }
        } while (num > books.size())  ;
        System.out.println("Book List");
        for (int i = 0; i < num ; i++) {
            System.out.println(books.get(ran.nextInt(books.size())).toString());
        }
    }  // done

    /**
     * Displays the main menu and returns the users choice in as an Int
     * checks for user input to make sure it returns an Int
     * @return Int choice
     */
    public int displayMenu() {
        System.out.println("Welcome in ABC Book Company: How May We Assist You?");
        System.out.println(
                        "1\tCheckout Book\n" +
                        "2\tCheckin Book\n" +
                        "3\tFind Books by Title\n" +
                        "4\tDisplay Books by Type\n" +
                        "5\tProduce Random Book List\n" +
                        "6\tSave & Exit\n");
        System.out.print("Enter an option: ");
        while(!keyBoard.hasNextInt()) {
            keyBoard.next();
            System.out.println("Enter an option: ");
        }
        return keyBoard.nextInt();
    } // done

    /**
     * Reads the text file in the res folder and puts the data into
     * book objects in their correct category.
     * Checks for the category off the last digit of the isbn number
     * @throws IOException IOException
     */
    public void readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(";");
            switch (temp[0].substring(temp[0].length() - 1)) {
                case "0":
                case "1":
                    childBook childB = new childBook(Long.parseLong(temp[0]), temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), temp[4], temp[5], temp[6].charAt(0));
                    books.add(childB);
                    break;
                case "2":
                case "3":
                    cookBook cookB = new cookBook(Long.parseLong(temp[0]), temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), temp[4], temp[5], temp[6].charAt(0));
                    books.add(cookB);
                    break;
                case "4":
                case "5":
                case "6":
                case "7":
                    paperBook paperB = new paperBook(Long.parseLong(temp[0]), temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), temp[4], new String[]{Arrays.toString(temp[5].split(", ")).replace("[", "").replace("]", "")}, Integer.parseInt(temp[6]), temp[7].charAt(0));
                    books.add(paperB);
                    break;
                case "8":
                case "9":
                    periodicals periodicalsB = new periodicals(Long.parseLong(temp[0]), temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), temp[4], temp[5].charAt(0));
                    books.add(periodicalsB);
                    break;
                default:
                    System.out.println("Did not read right");
            }
        }
        br.close();
    } //done

    /**
     * Saves the current Array list of books into the same file that
     * the program read out of before.
     * @throws IOException IOException
     */
    public void saveFile() throws IOException {
        PrintWriter bookWriter = new PrintWriter(filePath);
        for (book bb : books) {
            bookWriter.print(bb.saveString());
        }
        bookWriter.close();
    } // done
}