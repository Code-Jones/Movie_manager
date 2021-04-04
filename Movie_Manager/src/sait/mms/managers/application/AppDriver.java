package sait.mms.managers.application;

import java.io.IOException;
import java.util.ArrayList;
import sait.mms.managers.problemdomain.movie;
import sait.mms.managers.*;

/**
 *@author Matt Jones
 *@version 1.1
 *@throws IOException
 *PROGRAM: This program is a movie management system.
 *It records a given list of films and presents it to the user
 *in several different ways. It saves the file when the user exits
 *the program. 
 */
public class AppDriver {

	public static void main(String[] args) throws IOException {
		//object<movie> list
        ArrayList<movie> movieList = new ArrayList<>();
        //load data from file
        MovieManagementSystem.readFile(movieList);
        // prints only once
        System.out.println("Welcome to the movie management system");
        int choice;
        boolean running = true;
        while (running) {
            choice = MovieManagementSystem.displayMenu();
            switch (choice){
                case 1:
                    movieList.add(MovieManagementSystem.addMovie());
                    break;
                case 2:
                	MovieManagementSystem.generateMovieInYear(movieList);
                    break;
                case 3:
                	MovieManagementSystem.generateRandomMovie(movieList);
                    break;
                case 4:
                    System.out.println("goodbye");
                    running = false;
                break;

                default:
                    System.out.println("Invalid option, please try again.\n");
            }
        }
        //save data to file
        MovieManagementSystem.saveFile(movieList);
    }


	}
