package aocday9;

public class InvalidDistanceException extends RuntimeException {
    InvalidDistanceException(){
        super("Distance too large");
    }
}
