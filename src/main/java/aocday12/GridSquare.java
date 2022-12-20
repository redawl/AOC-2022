package aocday12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class GridSquare {
    private static final String VALUES = "abcdefghijklmnopqrstuvwxyz";
    private static final int INFINITY = 100;

    private final List<GridSquare> neighbors = new ArrayList<>();

    private final HashMap<GridSquare, Integer> weights = new HashMap<>();

    private final String name;

    private boolean visited = false;

    public GridSquare(List<String> map, int x, int y){
        // Set name
        name = x + ":" + y;

        int myValue = VALUES.indexOf(map.get(y).charAt(x));


    }
}
