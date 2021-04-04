package sait.mms.application;

import sait.mms.drivers.MariaDBDriver;
import sait.mms.managers.MovieManagementSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class AppDriver {

    /**
     * Main class for the Movie Management System
     * creates objects and then tests a connection to the
     * movie database. Then repeats a loop till the user is finished
     * Then closes the scanner and database connection.
     * @param args main
     * @throws SQLException On disconnect
     */
    public static void main(String[] args) throws SQLException {
        MariaDBDriver mariaDB = new MariaDBDriver();
        MovieManagementSystem mms = new MovieManagementSystem();
        Scanner keyBoard = new Scanner(System.in);
        try {
            mariaDB.connect();
            System.out.println("Welcome to the movie management system");
            int choice = 0;
            while (choice != 5) {
                choice = mms.displayMenu(keyBoard);
                switch (choice) {
                    case 1:
                        mms.addMovie(mariaDB, keyBoard);
                        break;
                    case 2:
                        mms.printMoviesInYear(mariaDB, keyBoard);
                        break;
                    case 3:
                        mms.printRandomMovies(mariaDB, keyBoard);
                        break;
                    case 4:
                        mms.deleteMovie(mariaDB, keyBoard);
                        break;
                    case 5:
                        System.out.println("Goodbye Ali");
                        break;
                    default:
                        System.out.println("Invalid option, please try again.\n");
                }
            }
            keyBoard.close();
            mariaDB.disconnect();
        } catch (Exception e) {
            System.out.println("Bad input Ali");
            e.printStackTrace();
        }
        
    }
}
