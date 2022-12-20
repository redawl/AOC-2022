package aocday9;

public class InvalidDirectionException extends RuntimeException {
    InvalidDirectionException(String direction){
        super("Invalid Direction: " + direction);
    }
}
