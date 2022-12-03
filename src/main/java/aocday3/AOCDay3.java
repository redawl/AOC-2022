package aocday3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOCDay3 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay3.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    // Dollar sign is to offset index of chars by 1
    private static final String alphabet = "$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static int challenge1(List<String> rucksacks){
        AtomicInteger total = new AtomicInteger();

        rucksacks.forEach(rucksack -> {
           String compartment1 = rucksack.substring(0, rucksack.length() / 2);
           String compartment2 = rucksack.substring(rucksack.length() / 2);
           assert compartment1.length() == compartment2.length();
           total.addAndGet((int) compartment1.chars().filter(ch -> compartment2.indexOf(ch) > -1)
                           .mapToDouble(alphabet::indexOf).findFirst()
                   .orElseThrow(FailedToFindItemException::new));
        });

        return total.get();
    }

    private static int findInAllThree(String str1, String str2, String str3){
        return (int) str1.chars().filter(ch1 -> str2.chars()
                .filter(ch2 -> ch2 == ch1).count() > 0 && str3.chars().filter(ch3 -> ch3 == ch1).count() > 0)
                .mapToDouble(alphabet::indexOf).findFirst().orElseThrow(FailedToFindItemException::new);
    }

    public static int challenge2(List<String> rucksacks){
        AtomicInteger total = new AtomicInteger();

        IntStream.iterate(0, i -> i + 3)
                .limit(rucksacks.size() / 3)
                .forEach(i -> total.addAndGet(findInAllThree(rucksacks.get(i), rucksacks.get(i + 1), rucksacks.get(i + 2))));

        return total.get();
    }

    public static void main(String [] args){
        List<String> rucksacks = fileUtils.getFileContents("challenge3.txt")
                        .collect(Collectors.toList());

        logger.info("Result: {}", challenge1(rucksacks));
        logger.info("Result: {}", challenge2(rucksacks));
    }
}
