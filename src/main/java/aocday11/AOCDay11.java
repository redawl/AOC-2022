package aocday11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOCDay11 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay11.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    public static long challenge1(List<List<String>> monkeyInput){
        return computer(monkeyInput, 20, 3L);
    }

    private static long computer(List<List<String>> monkeyInput, int rounds, long worryDivisor){
        List<Monkey> monkeys = new ArrayList<>();

        long productOfAllTests = monkeyInput.stream().mapToLong(m -> {
                    Matcher matcher = Monkey.monkeyNumberPattern.matcher(m.get(3));
                    if(matcher.find()){
                        return Long.parseLong(matcher.group(1));
                    }
                    throw new BadFormatException(m.get(3));
                }).reduce(1, (x,y) -> x * y);

        monkeyInput.forEach(input -> monkeys.add(new Monkey(input, worryDivisor, productOfAllTests)));

        IntStream.range(0, rounds)
                .forEach(i -> monkeys.forEach(monkey -> {
                    while(monkey.hasItems()) {
                        long receivingMonkey = monkey.receivingMonkey();
                        monkeys.get((int) receivingMonkey).catchItem(monkey.throwItem());
                    }
                }));

        List<Long> craziestMonkeys = monkeys.stream().mapToLong(Monkey::getNumberOfInspects).sorted()
                .boxed().collect(Collectors.toList());

        return craziestMonkeys.subList(craziestMonkeys.size() - 2, craziestMonkeys.size())
                .stream().reduce(1L, (x,y) -> x * y);
    }

    public static long challenge2(List<List<String>> monkeyInput){
        // TODO: Implement
        return computer(monkeyInput, 10000, 1L);

    }

    public static void main(String [] args){
        List<String> input = fileUtils.getFileContents("challenge11.txt", "\n\n")
                .collect(Collectors.toList());

        List<List<String>> monkeyInput = new ArrayList<>();

        input.forEach(in -> monkeyInput.add(Arrays.asList(in.split("\n"))));

        logger.info("Result: {}", challenge1(monkeyInput));
        logger.info("Result: {}", challenge2(monkeyInput));
    }
}
