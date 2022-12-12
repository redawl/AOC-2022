package aocday11;

public class BadFormatException extends RuntimeException {
    BadFormatException(String input){
        super("Bad input: "+ input);
    }
}
