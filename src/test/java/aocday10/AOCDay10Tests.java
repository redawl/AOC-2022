package aocday10;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOCDay10Tests {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay10Tests.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    private static final List<String> testData = fileUtils.getFileContents("challenge10.txt")
            .collect(Collectors.toList());
    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(13140, AOCDay10.challenge1(testData));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        String [][] screen = AOCDay10.challenge2(testData);

        String testData = "##..##..##..##..##..##..##..##..##..##..\n" +
                "###...###...###...###...###...###...###.\n" +
                "####....####....####....####....####....\n" +
                "#####.....#####.....#####.....#####.....\n" +
                "######......######......######......####\n" +
                "#######.......#######.......#######.....";
        StringBuilder builder = new StringBuilder();

        IntStream.range(0, 6)
                .forEachOrdered(i -> {
                    IntStream.range(0, 40)
                            .forEach(j -> builder.append( screen[i][j]));
                    builder.append("\n");
                });

        Assert.assertEquals(testData, builder.deleteCharAt(builder.length() - 1).toString());
    }
}
