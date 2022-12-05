package aocday5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOCDay5 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay5.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    private static List<Stack<String>> parseStack(List<String> crates){
        List<Stack<String>> stacks = new ArrayList<>();

        Pattern counterRowPattern = Pattern.compile("^ \\d+");

        String [] counterRow = crates.stream().filter(crate -> counterRowPattern.matcher(crate).find()).findFirst()
                .orElseThrow(() -> new RuntimeException("Oops")).split(" ");

        int rowLength = Integer.parseInt(counterRow[counterRow.length - 1]);

        IntStream.range(0, crates.size() - 1)
                .forEach(i -> IntStream.iterate(0, j -> j + 4)
                        .limit(rowLength)
                        .forEach(j -> {
                            if (i == 0) stacks.add(new Stack<>());
                            if(j + 3 <= crates.get(i).length()) {
                                String crate = crates.get(i).substring(j, j + 3).trim();
                                if(!"".equals(crate))
                                    stacks.get(j / 4).push(crate);
                            }
                        }));

        // Reverse the stacks
        List<Stack<String>> stacksReversed = new ArrayList<>();
        IntStream.range(0, stacks.size())
                .forEach(i -> {
                    stacksReversed.add(new Stack<>());

                    Stack<String> currStack = stacks.get(i);

                    while(!currStack.isEmpty()) stacksReversed.get(i).push(currStack.pop());

                });

        return stacksReversed;
    }

    private static String readTops(List<Stack<String>> stacks){
        StringBuilder answer = new StringBuilder();

        Pattern cratePattern = Pattern.compile("\\[([A-Z])]");

        stacks.forEach(stack -> {
            String crate = stack.peek();

            Matcher crateMatcher = cratePattern.matcher(crate);
            if(crateMatcher.find()){
                answer.append(crateMatcher.group(1));
            }
        });

        return answer.toString();
    }

    public static String challenge1(List<String> crates, List<String> moves){
        // Parse boxes
        List<Stack<String>> stacks = parseStack(crates);

        // Parse moves
        Pattern movePattern = Pattern.compile("(\\d+)[a-z ]+(\\d+)[a-z ]+(\\d+)");

        moves.forEach(move -> {
            Matcher moveMatcher = movePattern.matcher(move);

            if(moveMatcher.find()){
                int crateNum = Integer.parseInt(moveMatcher.group(1));
                int source = Integer.parseInt(moveMatcher.group(2));
                int destination = Integer.parseInt(moveMatcher.group(3));

                IntStream.range(0, crateNum)
                        .forEach(i -> {
                            String crate = stacks.get(source - 1).pop();
                            stacks.get(destination - 1).push(crate);
                        });
            }
        });

        // Read of tops
        return readTops(stacks);
    }

    public static String challenge2(List<String> crates, List<String> moves){
        List<Stack<String>> stacks = parseStack(crates);

        // Parse moves
        Pattern movePattern = Pattern.compile("(\\d+)[a-z ]+(\\d+)[a-z ]+(\\d+)");

        moves.forEach(move -> {
            Matcher moveMatcher = movePattern.matcher(move);

            if(moveMatcher.find()){
                int crateNum = Integer.parseInt(moveMatcher.group(1));
                int source = Integer.parseInt(moveMatcher.group(2));
                int destination = Integer.parseInt(moveMatcher.group(3));

                List<String> tempCrates = new ArrayList<>();
                IntStream.range(0, crateNum)
                        .forEach(i -> {
                            String crate = stacks.get(source - 1).pop();
                            tempCrates.add(crate);
                        });

                IntStream.range(0, tempCrates.size())
                        .forEach(i -> {
                            String crate = tempCrates.get(tempCrates.size() - i - 1);

                            stacks.get(destination - 1).push(crate);
                        });
            }
        });

        return readTops(stacks);
    }

    public static void main(String [] args){
        List<String> parts = fileUtils.getFileContents("challenge5.txt", "\n\n")
                        .collect(Collectors.toList());

        List<String> crates = Arrays.stream(parts.get(0).split("\n")).collect(Collectors.toList());

        List<String> moves = Arrays.stream(parts.get(1).split("\n")).collect(Collectors.toList());

        logger.info("Result: {}", challenge1(crates, moves));
        logger.info("Result: {}", challenge2(crates, moves));
    }
}
