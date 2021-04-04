package sait.frs.exception;

public class NullFlightException extends Exception {
    public NullFlightException() { super("Flight is missing."); }
}
