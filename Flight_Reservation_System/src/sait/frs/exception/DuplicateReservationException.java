package sait.frs.exception;

public class DuplicateReservationException extends Exception {
    public DuplicateReservationException() {super("Reservation already made");}
}
