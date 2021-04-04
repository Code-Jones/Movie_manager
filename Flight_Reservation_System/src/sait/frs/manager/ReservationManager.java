package sait.frs.manager;

import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException;
import sait.frs.exception.NoMoreSeatsException;
import sait.frs.exception.NullFlightException;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;

import java.io.*;
import java.util.ArrayList;

public class ReservationManager {

    String reservationPath = "res/reservations.bin";
    private ArrayList<Reservation> reservationsList = new ArrayList();

    public ReservationManager() {
        populateFromBinary();
    }

    public Reservation makeReservation(Flight selectedFlight, String name, String citizenship) throws NullFlightException, NoMoreSeatsException, InvalidNameException, InvalidCitizenshipException {
        if (selectedFlight == null) {
            throw new NullFlightException();
        }
        if (getAvailableSeats(selectedFlight) <= 0) {
            throw new NoMoreSeatsException();
        }
        if (name == null) {
            throw new InvalidNameException();
        }
        if (citizenship == null) {
            throw new InvalidCitizenshipException();
        }
//        persist();
        return new Reservation(selectedFlight, name, citizenship);

    }

    public ArrayList<Reservation> findReservations(String code, String airline, String name) {
        ArrayList<Reservation> returnList = new ArrayList<>();
        if (code == null && airline == null && name == null) {
            return null;
        } else {
            for (Reservation res : this.reservationsList) {
                if (res.getCode().equals(code) && res.getFlight().getAirline().equals(airline) && res.getName().equals(name)) {
                    returnList.add(res);
                }
            }
        }
        return returnList;
    }

    public Reservation findReservationByCode(String code) {
        if (code != null) {
            try {
                for (Reservation res : reservationsList) {
                    if (res.getCode().equals(code)) {
                        System.out.println("found reservation by code");
                        return res;
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception");
            }
        }
        return null;
    }

    public void persist() {
        try {
            FileOutputStream data = new FileOutputStream(reservationPath);
            DataOutputStream writer = new DataOutputStream(data);
            for (Reservation reservation : reservationsList) {
                writer.writeUTF(reservation.getCode());
                writer.writeUTF(reservation.getFlight().getCode());
                writer.writeUTF(reservation.getFlight().getAirline());
                writer.writeUTF(reservation.getName());
                writer.writeUTF(reservation.getCitizenship());
                writer.writeDouble(reservation.getFlight().getCostPerSeat());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAvailableSeats(Flight selectedFlight) {
        return selectedFlight.getSeats();
    }

    public String generateReservationCode(Flight flight) {
        return String.format("%s", Reservation.generateCodeFromFlight(flight));
    }

    public void populateFromBinary() {
        boolean endOfFile = false;
        try {
            FileInputStream data = new FileInputStream(reservationPath);
            DataInputStream inputFile = new DataInputStream(data);

            while (!endOfFile) {
                try {
                    boolean active = inputFile.readBoolean();
                    System.out.println(active);
                    String reservationCode = inputFile.readUTF();
                    System.out.println(reservationCode);
                    String flightCode = inputFile.readUTF();
                    System.out.println(flightCode);
                    String Airline = inputFile.readUTF();
                    System.out.println(Airline);
                    String name = inputFile.readUTF();
                    System.out.println(name);
                    String citizenship = inputFile.readUTF();
                    System.out.println(citizenship);
                    double cost = inputFile.readDouble();
                    System.out.println(cost);
                    Flight fly = this.findReservationByCode(reservationCode).getFlight();
                    Reservation res = new Reservation(reservationCode, fly, name, citizenship, active);
                    this.reservationsList.add(res);
                } catch (IOException e) {
                    endOfFile = true;
                } catch (NullFlightException | InvalidNameException | InvalidCitizenshipException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file not found");
        }
    }

    public ArrayList<Reservation> getReservations() {
        return this.reservationsList;
    }
}
