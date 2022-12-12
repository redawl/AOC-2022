package aocday8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOCDay8 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay8.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    public static int challenge1(List<List<Integer>> treeRows){
        AtomicInteger total = new AtomicInteger((treeRows.size() * 2) + (treeRows.get(0).size() * 2) - 4);

        int rowSize = treeRows.size() - 1;
        int colSize = treeRows.get(0).size() - 1;

        IntStream.range(1, rowSize)
                .forEach(i -> IntStream.range(1, colSize)
                        .forEach(j -> {
                            int currTreeHeight = treeRows.get(i).get(j);

                            List<Integer> left = treeRows.get(i).subList(0, j + 1);
                            List<Integer> right = treeRows.get(i).subList(j, rowSize + 1);
                            List<Integer> top = treeRows.stream().map(row -> row.get(j)).collect(Collectors.toList())
                                    .subList(0, i + 1);
                            List<Integer> bottom = treeRows.stream().map(row -> row.get(j)).collect(Collectors.toList())
                                    .subList(i, colSize + 1);

                            if (compare1(left, currTreeHeight)
                            || compare1(right, currTreeHeight)
                            || compare1(top, currTreeHeight)
                            || compare1(bottom, currTreeHeight)) {
                                total.addAndGet(1);
                            }
                        }));

        return total.get();
    }

    private static boolean compare1(List<Integer> list, int height){
        return list.stream().max(Integer::compare).orElseThrow(RuntimeException::new) == height
                && list.stream().filter(i -> i == height).count() == 1;
    }

    private static int compare2(List<Integer> list, int height){
        return compare2(list, height, false);
    }
    private static int compare2(List<Integer> list, int height, boolean reverse){
        if(reverse){
            int bound = list.size();
            for (int i = 1; i < bound; i++) {
                if (list.get(i) >= height) {
                    return i;
                }
            }
        } else {
            for (int i = list.size() - 2; i >= 0; i--) {
                if (list.get(i) >= height) {
                    return list.size() - i - 1;
                }
            }
        }

        return list.size() - 1;
    }

    public static int challenge2(List<List<Integer>> treeRows){
        // TODO: Implement
        List<Integer> scenicScores = new ArrayList<>();

        int rowSize = treeRows.size() - 1;
        int colSize = treeRows.get(0).size() - 1;

        IntStream.range(1, rowSize)
                .forEach(i -> IntStream.range(1, colSize)
                        .forEach(j -> {
                            int currTreeHeight = treeRows.get(i).get(j);

                            List<Integer> left = treeRows.get(i).subList(0, j + 1);
                            List<Integer> right = treeRows.get(i).subList(j, rowSize + 1);
                            List<Integer> top = treeRows.stream().map(row -> row.get(j)).collect(Collectors.toList())
                                    .subList(0, i + 1);
                            List<Integer> bottom = treeRows.stream().map(row -> row.get(j)).collect(Collectors.toList())
                                    .subList(i, colSize + 1);

                            scenicScores.add((compare2(left, currTreeHeight)
                                            * compare2(right, currTreeHeight, true)
                                            * compare2(top, currTreeHeight)
                                            * compare2(bottom, currTreeHeight, true)));
                        }));

        return scenicScores.stream().max(Integer::compare).orElseThrow(RuntimeException::new);
    }

    public static void main(String [] args){
        // TODO: Set up initial state
        List<List<Integer>> treeRows = new ArrayList<>();

        fileUtils.getFileContents("challenge8.txt")
                .forEach(row -> treeRows.add(row.chars().map(i -> i - '0').boxed().collect(Collectors.toList())));

        logger.info("Result: {}", challenge1(treeRows));
        logger.info("Result: {}", challenge2(treeRows));
    }
}
