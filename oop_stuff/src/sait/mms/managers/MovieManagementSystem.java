package sait.mms.managers;

import java.io.*;
import java.util.*;
import sait.mms.managers.problemdomain.movie;

/**
 * 
 * @author Matt Jones
 * @version 1.1 
 * Management System for the appDiver class
 *
 */
public class MovieManagementSystem {
	
	/**
	 * @param movieList
	 * @version 1.1
	 * creates a list of random movies
	 */
    public static void generateRandomMovie(ArrayList<movie> movieList) {
        Scanner keyBoard = new Scanner(System.in);
        Random ran = new Random();
        int num = 100000;
        while(num > movieList.size()) {
            System.out.print("Enter number of movies: ");
            num = keyBoard.nextInt();
            if (num > movieList.size()) {
                System.out.println("That's far to many, try again.");
            }
        }

        int totalTime = 0;
        System.out.println("Movie List");
        System.out.printf("%8s\t%4s\t %5s %n", "Duration", "Year", "Title");
        for (int i = 0; i < num ; i++) {
            int filmIndex = Integer.parseInt(String.valueOf(ran.nextInt(movieList.size())));
            System.out.printf("%3d %12d\t %s %n", movieList.get(filmIndex).getTime(), movieList.get(filmIndex).getYear(), movieList.get(filmIndex).getTitle());
            totalTime += movieList.get(filmIndex).getTime();
        }
        System.out.printf("Total duration: %d minutes %n", totalTime);
        keyBoard.close();
    }
    
    /**
     * @param movieList
     * @version 1.1
     * creates a list of movies produced in a given year
     */
    public static void generateMovieInYear(ArrayList<movie> movieList) {
        Scanner keyBoard = new Scanner(System.in);
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
        System.out.printf("%8s\t%4s\t %5s %n", "Duration", "Year", "Title");
        for (movie film: movieList) {
            if (film.getYear() == year) {
                System.out.printf("%3d %12d\t %s %n", film.getTime(), film.getYear(), film.getTitle());
                totalTime += film.getTime();
            }
        }
        System.out.printf("Total duration: %d minutes %n%n", totalTime);
        keyBoard.close();
    }
    
    /**
     * @return user's choice in integer (1-4)
     * @version 1.1
     * displays user menu and return user choice
     */
    public static int displayMenu(){
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("What option would you like to pick? ");
        System.out.println("1\tAdd New Movie and Save\n" +
                            "2\tGenerate List of Movies Released in a Year\n" +
                            "3\tGenerate List of Random Movies\n" +
                            "4\tExit\n");
        System.out.print("Enter an option: ");
        int justToCloseKeyBoard = keyBoard.nextInt();
        keyBoard.close();
        return justToCloseKeyBoard;
    }
    
    /**
     * @version 1.1
     * @return movie object with user's input as attributes
     * returns a new movie object with users inputs
     */
    public static movie addMovie() {
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("What's the run time of the movie ? ");
        int runTime = keyBoard.nextInt();
        keyBoard.nextLine();
        
        System.out.print("\nWhat's the movie's title ? ");
        String title = keyBoard.nextLine();
        
        System.out.print("\nWhat year was the movie published in ? ");
        int year = keyBoard.nextInt();
        
        keyBoard.close();
        return new movie(runTime, title, year);
    }
    
    /** 
     * @version 1.1
     * @param moviesList
     * @throws IOException
     * reads data.txt file and puts data into given Array list
     */
    public static void readFile(ArrayList<movie> moviesList) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./res/movies.txt"));
        // read each line and put into given object list
        br.lines().map(line -> line.split(",")).map(temp -> new movie(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]))).forEach(moviesList::add);
        br.close();
    }
    
    /**
     * @version 1.1
     * @param movieList
     * @throws IOException
     * save data from given array list to data.txt file
     */
    public static void saveFile(ArrayList<movie> movieList) throws IOException {
        PrintWriter movieWriter = new PrintWriter("./res/movies.txt");

        // print data to file
        for (movie film: movieList) {
            movieWriter.println(film.getTime() + "," + film.getTitle() + "," + film.getYear());
        }

        movieWriter.close();
    	}
    
    }
