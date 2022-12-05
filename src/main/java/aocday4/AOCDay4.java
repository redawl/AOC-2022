package aocday4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AOCDay4 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay4.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    public static int challenge1(List<String> assignments){
        AtomicInteger total = new AtomicInteger();

        final Pattern rangePattern = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");
        Consumer<String> oneContainsTheOther = assignment -> {
            Matcher assignmentMatcher = rangePattern.matcher(assignment);

            try {
                if(assignmentMatcher.find()) {
                    List<Integer> rangeNumbers = List.of(
                            Integer.parseInt(assignmentMatcher.group(1)),
                            Integer.parseInt(assignmentMatcher.group(2)),
                            Integer.parseInt(assignmentMatcher.group(3)),
                            Integer.parseInt(assignmentMatcher.group(4))
                    );

                    if ((rangeNumbers.get(0) >= rangeNumbers.get(2) && rangeNumbers.get(1) <= rangeNumbers.get(3))
                            || (rangeNumbers.get(0) <= rangeNumbers.get(2) && rangeNumbers.get(1) >= rangeNumbers.get(3)))
                        total.addAndGet(1);
                }
            } catch (NumberFormatException ex){
                throw new InvalidRangeFormatException();
            }
        };

        assignments.forEach(oneContainsTheOther);

        return total.get();
    }

    public static int challenge2(List<String> assignments){
        AtomicInteger total = new AtomicInteger();
        final Pattern rangePattern = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

        Consumer<String> oneOverlapsTheOther = assignment -> {
            Matcher assignmentMatcher = rangePattern.matcher(assignment);

            try {
                if(assignmentMatcher.find()) {
                    List<Integer> rangeNumbers = List.of(
                            Integer.parseInt(assignmentMatcher.group(1)),
                            Integer.parseInt(assignmentMatcher.group(2)),
                            Integer.parseInt(assignmentMatcher.group(3)),
                            Integer.parseInt(assignmentMatcher.group(4))
                    );

                    if (!((rangeNumbers.get(1) < rangeNumbers.get(2)) || (rangeNumbers.get(0) > rangeNumbers.get(3))))
                        total.addAndGet(1);
                }
            } catch (NumberFormatException ex){
                throw new InvalidRangeFormatException();
            }
        };

        assignments.forEach(oneOverlapsTheOther);

        return total.get();
    }

    public static void main(String [] args){
        List<String> assignments = fileUtils.getFileContents("challenge4.txt")
                .collect(Collectors.toList());

        logger.info("Result: {}", challenge1(assignments));
        logger.info("Result: {}", challenge2(assignments));
    }
}
