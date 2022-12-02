package aocday2;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AOCDay2Tests {
    private static final List<String> testCase = List.of("A Y",
            "B X",
            "C Z");

    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(15, AOCDay2.challenge1(testCase));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        Assert.assertEquals(12, AOCDay2.challenge2(testCase));
    }
}
