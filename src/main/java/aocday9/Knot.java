package aocday9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class Knot {
    private final AtomicInteger xPosition = new AtomicInteger();

    private final AtomicInteger yPosition = new AtomicInteger();

    public String getCoordinates(){
        return xPosition.get() + ":" + yPosition.get();
    }

    public void incrementX(){
        xPosition.addAndGet( 1);
    }

    public void decrementX(){
        xPosition.addAndGet(-1);
    }

    public void incrementY(){
        yPosition.addAndGet(1);
    }

    public void decrementY(){
        yPosition.addAndGet(-1);
    }

    public int getXPosition() {
        return xPosition.get();
    }

    public int getYPosition() {
        return yPosition.get();
    }

    public void computeNewPosition(Knot prevKnot){
        if(prevKnot.getYPosition() - yPosition.get() == 2) {
//            if(Math.abs(xPosition.get() - prevKnot.getXPosition()) >= 2)
//                throw new InvalidDistanceException();
            xPosition.set(prevKnot.getXPosition());
            incrementY();
        }

        if(yPosition.get() - prevKnot.getYPosition() == 2) {
//            if(Math.abs(xPosition.get() - prevKnot.getXPosition()) >= 2)
//                throw new InvalidDistanceException();
            xPosition.set(prevKnot.getXPosition());
            decrementY();
        }

        if(prevKnot.getXPosition() - xPosition.get() == 2){
//            if(Math.abs(yPosition.get() - prevKnot.getYPosition()) >= 2)
//                throw new InvalidDistanceException();
            yPosition.set(prevKnot.getYPosition());
            incrementX();
        }

        if(xPosition.get() - prevKnot.getXPosition() == 2){
//            if(Math.abs(yPosition.get() - prevKnot.getYPosition()) >= 2)
//                throw new InvalidDistanceException();
            yPosition.set(prevKnot.getYPosition());
            decrementX();
        }
    }

    public void computeHeadPosition(String direction){
        switch (direction) {
            case "U":
                incrementY();
                break;
            case "D":
                decrementY();
                break;
            case "R":
                incrementX();
                break;
            case "L":
                decrementX();
                break;
            default:
                throw new InvalidDirectionException(direction);
        }
    }
}
