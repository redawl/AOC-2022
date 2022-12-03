package aocday1;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

public class AOCDay1Tests {
    private final List<Integer> testCase = AOCDay1.compressCalories(Stream.of(
            "1000",
            "2000\n3000",
            "4000",
            "5000\n6000",
            "7000\n8000\n9000",
            "10000"
    ));

    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(24000, AOCDay1.challenge1(testCase));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        Assert.assertEquals(45000, AOCDay1.challenge2(testCase));
    }

}
