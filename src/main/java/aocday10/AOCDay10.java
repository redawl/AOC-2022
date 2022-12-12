package aocday10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOCDay10 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay10.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    private static final int X_PIXELS = 40;

    private static final int Y_PIXELS = 6;

    public static int challenge1(List<String> commands){
        List<Integer> importantCycles = List.of(
          20, 60, 100, 140, 180, 220
        );

        AtomicInteger total = new AtomicInteger();

        AtomicInteger clock = new AtomicInteger();

        AtomicInteger registerX = new AtomicInteger(1);

        Pattern addPattern = Pattern.compile("(-?\\d+)");

        commands.forEach(command -> {
            clock.addAndGet(1);
            if(importantCycles.contains(clock.get())){
                total.addAndGet(registerX.get() * clock.get());
            }

            if(!"noop".equals(command)) {
                Matcher commandMatcher = addPattern.matcher(command);
                if(commandMatcher.find()){
                    int amount = Integer.parseInt(commandMatcher.group(1));

                    clock.addAndGet(1);
                    if(importantCycles.contains(clock.get())){
                        total.addAndGet(registerX.get() * clock.get());
                    }
                    registerX.addAndGet(amount);


                }
            }
        });

        return total.get();
    }

    public static String [][] challenge2(List<String> commands){

        String [][] screen = new String[Y_PIXELS][X_PIXELS];

        AtomicInteger clock = new AtomicInteger();

        AtomicInteger registerX = new AtomicInteger(1);

        Pattern addPattern = Pattern.compile("(-?\\d+)");

        commands.forEach(command -> {
            clock.addAndGet(1);
            generatePixel(clock.get(),registerX.get(), screen);

            if(!"noop".equals(command)) {
                Matcher commandMatcher = addPattern.matcher(command);
                if(commandMatcher.find()){
                    int amount = Integer.parseInt(commandMatcher.group(1));

                    clock.addAndGet(1);
                    generatePixel(clock.get(), registerX.get(), screen);
                    registerX.addAndGet(amount);
                }
            }
        });

        return screen;
    }

    private static void generatePixel(int clock, int registerValue, String [][] screen){
        int x = (clock - 1) % X_PIXELS;
        screen[(clock - 1) / X_PIXELS][x] = registerValue - 1 <= x && registerValue + 1 >= x ? "#" : ".";
    }

    public static void main(String [] args){
        List<String> commands = fileUtils.getFileContents("challenge10.txt")
                        .collect(Collectors.toList());

        logger.info("Result: {}", challenge1(commands));

        String [][] screen = challenge2(commands);
        IntStream.range(0, Y_PIXELS)
                        .forEachOrdered(i -> {
                            IntStream.range(0, X_PIXELS)
                                .forEachOrdered(j -> System.out.printf(" %s ", screen[i][j]));
                            System.out.println();
                        });
    }
}
