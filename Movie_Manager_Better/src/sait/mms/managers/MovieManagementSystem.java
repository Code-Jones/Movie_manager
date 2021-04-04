package sait.mms.managers;

import sait.mms.drivers.MariaDBDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @author Matt Jones
 * @version 1.1
 * MovieManagementSystem class holds all the functions for the appDriver
 * Functions included are displayMenu, addMovie, deleteMovie, printMoviesInYear, and printRandomMovies
 */
public class MovieManagementSystem {

    /**
     * DisplayMenu shows the user menu to interact with the functions.
     * Returns the users input in the form of an integer
     * @param keyBoard Standard Scanner
     * @return Users input by choice
     */
    public int displayMenu(Scanner keyBoard) {
        System.out.println(
                "What option would you like to pick? \n" +
                        "1\tAdd New Movie and Save\n" +
                        "2\tGenerate List of Movies Released in a Selected Year\n" +
                        "3\tGenerate List of Random Movies\n" +
                        "4\tDelete a Movie from a Selected Id\n" +
                        "5\tExit\n");
        System.out.print("Enter an option: ");
        return keyBoard.nextInt();
    }

    /**
     * AddMovie adds a movie to the Movies database
     * Checks users input beforehand
     * @param mariaDB Database driver
     * @param keyBoard standard scanner
     */
    public void addMovie(MariaDBDriver mariaDB, Scanner keyBoard) {
        keyBoard.nextLine();
        System.out.print("What's the movie's title ? ");
        while (!keyBoard.hasNextLine()) {
            System.out.print("What's the movie's title ? ");
            keyBoard.next();
        }
        String title = keyBoard.nextLine();

        System.out.print("What's the duration of the movie ? ");
        while (!keyBoard.hasNextInt()) {
            keyBoard.next();
            System.out.print("What's the run time of the movie ? ");
        }
        int runTime = keyBoard.nextInt();
        keyBoard.nextLine();

        System.out.print("What year was the movie published in ? ");
        while (!keyBoard.hasNextInt()) {
            keyBoard.next();
            System.out.print("What year was the movie published in ? ");
        }
        int year = keyBoard.nextInt();
        try {
            mariaDB.connect();
            mariaDB.update(String.format("Insert into movies (duration, title, year) values (%d, \"%s\", %d)", runTime, title, year));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\n");

    }

    /**
     * DeleteMovie deletes a movie from the Movies database
     * using the id number of the movie. How they know they number?
     * well, who knows. That's how you wanted it Ali.
     * @param mariaDB Database Driver
     * @param keyBoard Standard Scanner
     */
    public void deleteMovie(MariaDBDriver mariaDB, Scanner keyBoard) {
        keyBoard.nextLine();
        System.out.print("What's the movie's id ? ");
        while (!keyBoard.hasNextInt()) {
            System.out.print("What's the movie's title ? ");
            keyBoard.next();
        }
        int id = keyBoard.nextInt();
        try {
            mariaDB.update(String.format("delete from movies where id = %d", id));
            System.out.println("Movie Successfully Deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * PrintMoviesInYear takes user input to get a selected year
     * then prints all the movies found in the Movies database with
     * the same year
     * @param mariaDB Database Driver
     * @param keyBoard Standard Scanner
     */
    public void printMoviesInYear(MariaDBDriver mariaDB, Scanner keyBoard) {
        int totalTime = 0;
        Calendar now = Calendar.getInstance();
        System.out.print("What year would you like to specify ? ");
        int year = keyBoard.nextInt();
        while (year > now.get(Calendar.YEAR) && year > 1949) {
            System.out.println("We can't tell the future, try again: ");
            year = keyBoard.nextInt();
        }
        keyBoard.nextLine();
        System.out.println("Movie List");
        System.out.printf("%15s\t%20s %3s %n", "Title", "Year", "Duration");
        try {
            ResultSet rs = mariaDB.get(String.format("select title, year, duration from movies where year = %d", year));
            while (rs.next()) {
                System.out.printf("%-30s %5s %5s %n", rs.getString(1), rs.getString(2).substring(0, 4), rs.getString(3));
                totalTime += Integer.parseInt(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("%nTotal Duration: %d Minutes %n%n", totalTime);
    }

    /**
     * PrintRandomMovies takes user input to get a selected amount
     * of movies to be printed. It then prints movies from the Movies
     * Database at random
     * @param mariaDB Database Driver
     * @param keyBoard Standard Scanner
     */
    public void printRandomMovies(MariaDBDriver mariaDB, Scanner keyBoard) {
        int totalTime = 0;
        int num;
        try {
            System.out.print("Enter number of movies: ");
            num = keyBoard.nextInt();
            System.out.println("Movie List");
            System.out.printf("%-4s %10s\t %10s  %n", "Year", "Duration", "Title");
            ResultSet rs = mariaDB.get(String.format("select title, year, duration from movies order by rand() limit %d", num));
            while (rs.next()) {
                System.out.printf("%-4s %10s %-1s %n", rs.getString(2).substring(0, 4), rs.getString(3), rs.getString(1));
                totalTime += Integer.parseInt(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("%nTotal duration: %d minutes %n%n", totalTime);
    }

}
