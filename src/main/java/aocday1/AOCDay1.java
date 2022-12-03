package aocday1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class AOCDay1 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay1.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    public static int challenge1(List<Integer> caloriesPerElf){
        return caloriesPerElf.stream().max(Integer::compare)
                .orElseThrow(() -> new RuntimeException("Failure :("));
    }

    public static int challenge2(List<Integer> caloriesPerElf){
        int totalTopThree = 0;
        for(int i = 0; i < 3; i++){
            int max = caloriesPerElf.stream().max(Integer::compare)
                    .orElseThrow(() -> new RuntimeException("Failure :("));
            totalTopThree += max;
            caloriesPerElf.remove( (Object) max);
        }
         return totalTopThree;
    }

    public static List<Integer> compressCalories(Stream<String> rawData){
        List<Integer> caloriesPerElf = new ArrayList<>();
        rawData.forEach(elvesCalories -> {
            AtomicInteger total = new AtomicInteger(0);
            Arrays.stream(elvesCalories.split("\n"))
                    .forEach(calorie -> total.getAndAdd(Integer.parseInt(calorie)));
            caloriesPerElf.add(total.get());
        });

        return caloriesPerElf;
    }

    public static void main(String [] args){
        // Find total calories per elf

        List<Integer> caloriesPerElf = compressCalories(fileUtils.getFileContents("challenge1.txt", "\n\n"));

        logger.info("Total top three: {}", challenge1(caloriesPerElf));
        logger.info("Most calories: {}", challenge2(caloriesPerElf));
    }
}
