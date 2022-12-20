package aocday9;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AOCDay9Tests {

    private final List<String> testData = List.of(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"
    );

    private final List<String> testData2 = List.of(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20"
    );

    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(13, AOCDay9.challenge1(testData));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        Assert.assertEquals(36,AOCDay9.challenge2(testData2));
    }
}
