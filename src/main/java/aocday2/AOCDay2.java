package aocday2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AOCDay2 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay2.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    private enum Shape {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        private final int value;

        Shape(int value){this.value = value;}

        int value(){
            return value;
        }

        Shape lose(Shape shape){
            Shape loseMove = PAPER;
            switch (shape){
                case ROCK:
                    loseMove = SCISSORS;
                    break;
                case PAPER:
                    loseMove = ROCK;
                    break;
                case SCISSORS:
                    loseMove = PAPER;
                    break;
            }

            return loseMove;
        }

        Shape win(Shape shape){
            switch (shape){
                case ROCK:
                    return PAPER;
                case PAPER:
                    return SCISSORS;
                case SCISSORS:
                    return ROCK;
            }
            throw new RuntimeException("Impossible");
        }

        int outcome(Shape shape){
            int outcome = 0;
            if(this.value == shape.value) {
                outcome = 3;
            } else {
                switch (this) {
                    case ROCK:
                        outcome = shape == PAPER ? 0 : 6;
                        break;
                    case PAPER:
                        outcome = shape == SCISSORS ? 0 : 6;
                        break;
                    case SCISSORS:
                        outcome = shape == ROCK ? 0 : 6;
                        break;
                }
            }

            return outcome;
        }

        int outcome2(Shape shape){
            int outcome = 0;
            switch (this){
                case ROCK:
                    outcome = lose(shape).value;
                    break;
                case PAPER:
                    outcome = 3 + shape.value;
                    break;
                case SCISSORS:
                    outcome = 6 + win(shape).value;
            }

            return outcome;
        }
    }

    private static final Map<String, Shape> opponent = Map.of(
            "A", Shape.ROCK,
            "B", Shape.PAPER,
            "C", Shape.SCISSORS
    );
    private static final Map<String, Shape> player = Map.of(
            "X", Shape.ROCK,
            "Y", Shape.PAPER,
            "Z", Shape.SCISSORS
    );

    public static int challenge1(List<String> moves){
        AtomicInteger score = new AtomicInteger();
        moves.forEach(move -> {
            String[] moveParts = move.split(" ");
            Shape pl = player.get(moveParts[1]);
            Shape op = opponent.get(moveParts[0]);
            score.addAndGet(pl.value() + pl.outcome(op));
        });
        return score.get();
    }

    public static int challenge2(List<String> moves){
        AtomicInteger score = new AtomicInteger();

        moves.forEach(move -> {
            String[] moveParts = move.split(" ");
            Shape pl = player.get(moveParts[1]);
            score.addAndGet(pl.outcome2(opponent.get(moveParts[0])));

        });

        return score.get();
    }

    public static void main(String [] args){
        List<String> moves = fileUtils.getFileContents("challenge2.txt")
                .collect(Collectors.toList());

        logger.info("Total score: {}", challenge1(moves));
        logger.info("Total score: {}", challenge2(moves));
    }
}
