package sait.frs.problemdomain;

import java.util.Random;
import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException;
import sait.frs.exception.NullFlightException;

public final class Reservation {
    private String code;
    private Flight flight;
    private String name;
    private String citizenship;
    private double cost;
    private boolean active;

    public Reservation(Flight flight, String name, String citizenship) throws NullFlightException, InvalidNameException, InvalidCitizenshipException {
        this.code = generateCodeFromFlight(flight);
        this.setFlight(flight);
        this.setName(name);
        this.setCitizenship(citizenship);
        this.active = true;
    }

    public Reservation(String code, Flight flight, String name, String citizenship, boolean active) throws NullFlightException, InvalidNameException, InvalidCitizenshipException {
        this.code = code;
        this.setFlight(flight);
        this.setName(name);
        this.setCitizenship(citizenship);
        this.active = active;
    }

    public String getCode() {
        return this.code;
    }

    public Flight getFlight() {
        return this.flight;
    }

    public String getName() {
        return this.name;
    }

    public void setFlight(Flight flight) throws NullFlightException {
        if (flight == null) {
            throw new NullFlightException();
        } else {
            this.flight = flight;
        }
    }

    public void setName(String name) throws InvalidNameException {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new InvalidNameException();
        }
    }

    public String getCitizenship() {
        return this.citizenship;
    }

    public void setCitizenship(String citizenship) throws InvalidCitizenshipException {
        if (citizenship != null && !citizenship.isEmpty()) {
            this.citizenship = citizenship;
        } else {
            throw new InvalidCitizenshipException();
        }
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static String generateCodeFromFlight(Flight flight) {
        int letter = (flight.isDomestic() ? 68 : 73);
        Random rand = new Random();
        return String.format("%c%d", (char) letter, rand.nextInt(9999) + 1000);
    }

    public String toString() {
        return this.getCode();
    }
}
