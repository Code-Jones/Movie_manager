package sait.mms.managers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import sait.mms.application.BookManagementSystem;
import sait.mms.problemdomain.book;

/**
 * @author Matt Jones
 * @version jan 29, 2020
 * This program is a book management system that allows the users to
 * keep track of the current books in the system. Users can search by name,
 * type of book or the program can create a random list for users.
 * Users can checkout a book by putting into the isbn number of the selected
 * book and the availability will drop of that book.
 */
public class Main {

    /**
     * to_do
     * fix toString format to actual word
     * fix author list
     */

    public static void main(String[] args) throws IOException {
        //object list
        Scanner keyBoard = new Scanner(System.in);
        ArrayList<book> books = new ArrayList<>();
        String filePath = "./res/books.txt";
        BookManagementSystem BMS = new BookManagementSystem(keyBoard, books, filePath);
        //load data
        BMS.readFile();
        // run main loop
        boolean running = true;
        while (running) {
            switch (BMS.displayMenu()) {
                case 1:
                    BMS.checkoutBook();
                    break;
                case 2:
                    BMS.checkIn();
                    break;
                case 3:
                    BMS.searchTitle();
                    break;
                case 4:
                    BMS.showByType();
                    break;
                case 5:
                    BMS.randomList();
                    break;
                case 6:
                    System.out.println("goodbye ali");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.\n");
            }
        }
        //Scanner keyBoard = new Scanner(System.in);
        BMS.saveFile();
        keyBoard.close();
    }
}