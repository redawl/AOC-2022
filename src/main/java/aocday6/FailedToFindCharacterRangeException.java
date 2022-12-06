package aocday6;

public class FailedToFindCharacterRangeException extends RuntimeException{
    FailedToFindCharacterRangeException(int amount){
        super(String.format("Failed to find %d unique characters in a row.", amount));
    }
}
