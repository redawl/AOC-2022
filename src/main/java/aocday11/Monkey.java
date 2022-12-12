package aocday11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monkey {
    private final List<Long> items;

    private final Function<Long, Long> operation;

    private final Supplier<Boolean> testOperation;

    private final List<Long> friendMonkeys;

    private final long test;

    private static final Pattern startingItemsPattern = Pattern.compile("(\\d+){1,100}");
    private static final Pattern operationPattern = Pattern.compile("([*\\-+/]) (.+)");

    public static final Pattern monkeyNumberPattern = Pattern.compile("(\\d+)");

    private final AtomicLong numberOfInspects = new AtomicLong();

    private final long worryDivisor;

    private final long productOfAllTests;

    public Monkey(List<String> input, long worryDivisor, long productOfAllTests){
        // Parse starting items
        items = parseStartingItems(input.get(1));

        // Parse operation
        operation = parseOperation(input.get(2));

        test = parseTest(input.get(3));

        friendMonkeys = parseFriendMonkeys(input.get(4), input.get(5));

        testOperation = () -> items.get(0) % test == 0;

        this.worryDivisor = worryDivisor;

        this.productOfAllTests = productOfAllTests;
    }

    private long parseTest(String input){
        Matcher testMatcher = monkeyNumberPattern.matcher(input);

        if(testMatcher.find()){
            return Long.parseLong(testMatcher.group(1));
        }

        throw new BadFormatException(input);
    }

    private List<Long> parseStartingItems(String input){
        List<Long> startingItems = new ArrayList<>();
        Matcher startingItemsMatcher = startingItemsPattern.matcher(input);
        while(startingItemsMatcher.find()){
            startingItems.add(Long.parseLong(startingItemsMatcher.group(1)));
        }

        return startingItems;
    }

    private Function<Long, Long> parseOperation(String input){
        Matcher operationMatcher = operationPattern.matcher(input);
        if(operationMatcher.find()){
            if("old".equals(operationMatcher.group(2))){
                switch (operationMatcher.group(1)) {
                    case "*":
                        return x -> (x * x) % productOfAllTests;
                    case "+":
                        return x -> (x + x) % productOfAllTests;
                    case "-":
                        return x -> 0L;
                    case "/":
                        return x -> 1L;
                    default:
                        throw new BadFormatException(input);

                }
            } else {
                long amount = Long.parseLong(operationMatcher.group(2));

                switch (operationMatcher.group(1)) {
                    case "*":
                        return x -> (x * amount) % productOfAllTests;
                    case "+":
                        return x -> (x + amount) % productOfAllTests;
                    case "-":
                        return x -> x - amount;
                    case "/":
                        return x -> x / amount;
                    default:
                        throw new BadFormatException(operationMatcher.group(1));
                }
            }
        }

        throw new BadFormatException(input);
    }

    private List<Long> parseFriendMonkeys(String monkey1, String monkey2){
        List<Long> monkees = new ArrayList<>();

        Matcher monkey1Matcher = monkeyNumberPattern.matcher(monkey1);
        if(monkey1Matcher.find()){
            monkees.add(Long.parseLong(monkey1Matcher.group(1)));
        }

        Matcher monkey2Matcher = monkeyNumberPattern.matcher(monkey2);
        if(monkey2Matcher.find()){
            monkees.add(Long.parseLong(monkey2Matcher.group(1)));
        }

        return monkees;
    }
    public long throwItem(){
        long item = items.get(0);

        items.remove(0);
        return item;
    }

    public long receivingMonkey(){
        numberOfInspects.addAndGet(1);
        items.set(0, operation.apply(items.get(0)) / worryDivisor);
        return Boolean.TRUE.equals(testOperation.get()) ? friendMonkeys.get(0): friendMonkeys.get(1);
    }

    public void catchItem(long item){
        items.add(item);
    }

    public boolean hasItems(){
        return !items.isEmpty();
    }

    public long getNumberOfInspects(){
        return numberOfInspects.get();
    }
}
