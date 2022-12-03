package aocday3;

public class FailedToFindItemException extends RuntimeException {
    FailedToFindItemException(){
        super("Failed to find item!");
    }
}
