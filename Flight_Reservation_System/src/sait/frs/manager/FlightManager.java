package sait.frs.manager;

import sait.frs.exception.*;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class FlightManager {


    public static final String WEEKDAY_ANY = "Any";
    public static final String WEEKDAY_MONDAY = "Monday";
    public static final String WEEKDAY_TUESDAY = "Tuesday";
    public static final String WEEKDAY_WEDNESDAY = "Wednesday";
    public static final String WEEKDAY_THURSDAY = "Thursday";
    public static final String WEEKDAY_FRIDAY = "Friday";
    public static final String WEEKDAY_SATURDAY = "Saturday";
    public static final String WEEKDAY_SUNDAY = "Sunday";
    private ArrayList<Flight> flightsList = new ArrayList();
    private ArrayList<String> airportsList = new ArrayList();
    private final String flightPath = "res/flights.csv";
    private final String airportPath = "res/airports.csv";
    /*
    I know you want this but it really could be a local variable or -
    written into the reader itself. Just extra unnecessary variables. -
    anyone worth their weight should be able to press contr + f to find -
    a file path.
     */

    public FlightManager () {
        this.populateFlights();
        this.populateAirports();
    }


    public ArrayList<String> getAirports() { return new ArrayList<>(this.airportsList);}

    public ArrayList<Flight> getFlights() { return new ArrayList<>(this.flightsList);}

    public String findAirportsByCode(String code) {
        if (code == null) {
            return null;
        } else {
            for (String airCode : airportsList) {
                if (getAirports().contains(airCode)) {
                    return airCode;
                }
            }
        }
        return null;
    }

    public Flight findFlightByCode(String code) {
        if (code == null) {
            return null;
        } else {
            for (Flight fly : flightsList) {
                if ( fly.getCode().equals(code)) {
                    return fly;
                }
            }
        }
        return null;
    }

    public ArrayList<Flight> findFlights(String from, String to, String weekday) {
        ArrayList<Flight> returnList = new ArrayList<>();
        System.out.println("find flights");
        if (!from.equals(to)) {
            for (Flight fly: this.flightsList) {
                if (fly.getFrom().equals(from) && fly.getTo().equals(to) && (weekday.equals(FlightManager.WEEKDAY_ANY) || fly.getWeekday().equals(weekday))) {
                    returnList.add(fly);
                }
            }
        } else {
            return null;
        }
        System.out.println("returning");
        return returnList;
    }

    public void populateFlights() {
        try {
            BufferedReader read = new BufferedReader(new FileReader(flightPath));
            while (read.readLine() != null) {
                String[] cells = read.readLine().split(",");
    //            String code, String airline, String from, String to, String weekday, String time, int seats, double costPerSeat
                Flight fly = new Flight(cells[0], "", cells[1], cells[2], cells[3], cells[4], Integer.parseInt(cells[5]), Double.parseDouble(cells[6]));
                this.flightsList.add(fly);
            }
        } catch (IOException e) {
            System.out.println("FLight file not found");
            e.printStackTrace();
            errorMessage("Flight file not found");
        }
    }

    public void populateAirports() {
        try {
            BufferedReader read = new BufferedReader(new FileReader(airportPath));
            String code = "";
            boolean quickFix = true;
            while ((code = read.readLine()) != null) {
                if (quickFix = true) { // you like that ali ? that's what i like to call a hack job. quick and dirty
                    this.airportsList.add(code.substring(0,3));
                    quickFix = false;
                }
                String[] cells = read.readLine().split(",");
                this.airportsList.add(cells[0]);
            }
        } catch (IOException e) {
            System.out.println("Airport file not found");
            e.printStackTrace();
            errorMessage("Airport file not found");
        }
    }

    public void errorMessage(String message){ JOptionPane.showMessageDialog(null, message); }
}
