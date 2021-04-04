package sait.frs.exception;

public class NoMoreSeatsException extends Exception {
    public NoMoreSeatsException() { super("Flight is completely booked.");}
}
