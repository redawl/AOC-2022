package aocday4;

public class InvalidRangeFormatException extends RuntimeException {
    InvalidRangeFormatException(){
        super("Range was in unexpected format.");
    }
}
