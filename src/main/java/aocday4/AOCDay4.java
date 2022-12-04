package aocday4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AOCDay4 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay4.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    public static int challenge1(List<String> assignments){
        AtomicInteger total = new AtomicInteger();

        Consumer<String> oneContainsTheOther = assignment -> {
            String [] ranges = assignment.split(",");
            try {
                int[] range1 = Arrays.stream(ranges[0].split("-")).mapToInt(Integer::parseInt).toArray();
                int[] range2 = Arrays.stream(ranges[1].split("-")).mapToInt(Integer::parseInt).toArray();

                if(oneInsideTheOther(range1, range2))
                    total.addAndGet(1);
            } catch (NumberFormatException ex){
                throw new InvalidRangeFormatException();
            }
        };

        assignments.forEach(oneContainsTheOther);

        return total.get();
    }

    public static int challenge2(List<String> assignments){
        AtomicInteger total = new AtomicInteger();

        Consumer<String> oneOverlapsTheOther = assignment -> {
            String [] ranges = assignment.split(",");
            try {
                int [] range1 = Arrays.stream(ranges[0].split("-")).mapToInt(Integer::parseInt).toArray();
                int [] range2 = Arrays.stream(ranges[1].split("-")).mapToInt(Integer::parseInt).toArray();

                if(checkForSimpleOverlap(range1, range2))
                    total.addAndGet(1);
            } catch (NumberFormatException ex){
                throw new InvalidRangeFormatException();
            }
        };

        assignments.forEach(oneOverlapsTheOther);

        return total.get();
    }

    /**
     * Check if two arrays overlap in any way
     * @param range1 First list to compare
     * @param range2 Second list to compare
     * @return True if they overlap, false if they don't
     */
    private static boolean checkForSimpleOverlap(int [] range1, int [] range2){
        return !((range1[1] < range2[0]) || (range1[0] > range2[1]));
    }

    /**
     * Check if one range is inside the other
     * @param range1 First range to check
     * @param range2 Second range to check
     * @return True if one is inside the other, false if not
     */
    private static boolean oneInsideTheOther(int [] range1, int [] range2){
        return (range1[0] >= range2[0] && range1[1] <= range2[1])
                || (range1[0] <= range2[0] && range1[1] >= range2[1]);
    }

    public static void main(String [] args){
        List<String> assignments = fileUtils.getFileContents("challenge4.txt")
                .collect(Collectors.toList());

        logger.info("Result: {}", challenge1(assignments));
        logger.info("Result: {}", challenge2(assignments));
    }
}
