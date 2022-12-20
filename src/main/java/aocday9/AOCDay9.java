package aocday9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOCDay9 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay9.class);
    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    public static int challenge1(List<String> moves){
        return computer(2, moves);
    }

    public static int challenge2(List<String> moves){
        // TODO: Implement
        return computer(10, moves);
    }

    private static int computer(int numKnots, List<String> moves){
        Set<String> positions = Collections.synchronizedSet(new HashSet<>());

        List<Knot> knots = new ArrayList<>();
        IntStream.range(0, numKnots)
                .forEach(i -> knots.add(new Knot()));

        moves.forEach(move -> {
            String [] moveParts = move.split(" ");
            String direction = moveParts[0];
            int step = Integer.parseInt(moveParts[1]);
            for(int i = 0; i < step; i++) {
                knots.get(0).computeHeadPosition(direction);
                IntStream.range(1, numKnots)
                        .forEachOrdered(j -> knots.get(j).computeNewPosition(knots.get(j - 1)));

                positions.add(knots.get(numKnots - 1).getCoordinates());
            }
        });

        print(positions);
        return positions.size();
    }

    private static void print(Set<String> positions){
        int range  = 100;
        IntStream.range(-range,range)
                .forEach(i -> {
                    IntStream.range(-range, range)
                            .forEach(j -> System.out.print(positions.contains(j + ":" + -i) ? " # " : " . "));
                    System.out.println();
                });
    }

    public static void main(String [] args){
        List<String> moves = fileUtils.getFileContents("challenge9.txt")
                        .collect(Collectors.toList());

        //logger.info("Result: {}", challenge1(moves));
        logger.info("Result: {}", challenge2(moves));
    }
}
