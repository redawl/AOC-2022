package aocday4;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AOCDay4Tests {

    private static final List<String> testData = List.of(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8"
    );

    @Test
    public void exampleCaseShouldPassChallenge1() {
        Assert.assertEquals(2, AOCDay4.challenge1(testData));
    }

    @Test
    public void exampleCaseShouldPassChallenge2() {
        Assert.assertEquals(4, AOCDay4.challenge2(testData));
    }

}
