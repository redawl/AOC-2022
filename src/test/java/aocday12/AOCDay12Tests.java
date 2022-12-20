package aocday12;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AOCDay12Tests {
    private final List<String> testData = List.of(
            "Sabqponm",
            "abcryxxl",
            "accszExk",
            "acctuvwj",
            "abdefghi"
    );
    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(31, AOCDay12.challenge1(testData));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){

    }
}
