package sait.frs.problemdomain;

import java.text.DecimalFormat;

public final class Flight {
    private String code;
    private String airline;
    private String from;
    private String to;
    private String weekday;
    private String time;
    private int seats;
    private double costPerSeat;

    public Flight(String code, String airline, String from, String to, String weekday, String time, int seats, double costPerSeat) {
        this.code = code;
        this.airline = airline;
        this.from = from;
        this.to = to;
        this.weekday = weekday;
        this.time = time;
        this.seats = seats;
        this.costPerSeat = costPerSeat;
    }

    public String getCode() {
        return parseCode(this.code);
    }

    public String getAirline() {
        return this.airline;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public String getWeekday() {
        return this.weekday;
    }

    public String getTime() {
        return this.time;
    }

    public int getSeats() {
        return this.seats;
    }

    public double getCostPerSeat() {
        return this.costPerSeat;
    }

    public boolean isDomestic() {
        return this.getFrom().charAt(0) == 'Y' && this.getTo().charAt(0) == 'Y';
    }

//    public boolean equals(Object obj) { // delete
//        if (!(obj instanceof Flight)) {
//            return false;
//        } else {
//            Flight other = (Flight) obj;
//            return this.getCode().equals(other.getCode());
//        }
//    } // old code / might not needed

    private String parseCode(String code) { // this literally does nothing if it's void / we could even delete the bad code if it wasn't private
        if (!(code.contains("OA") || code.contains("CA") || code.contains("TB") || code.contains("VA"))) {
            System.out.println("bad data " + code);
            code = "XX-" + code.substring(3,7);
            return code;
        }
        return code;
    } // meh?

    public String toString() {
        DecimalFormat thinkYouCouldHaveComeUpWithABetterVariableNameThen_df = new DecimalFormat("0.00");
        return this.getCode() + ", From: " + this.getFrom() + ", To: " + this.getTo() + ", Day: " + this.getWeekday() + ", Cost: " + thinkYouCouldHaveComeUpWithABetterVariableNameThen_df.format(this.getCostPerSeat());
    }
}
