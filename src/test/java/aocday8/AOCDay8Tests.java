package aocday8;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AOCDay8Tests {

    private final List<List<Integer>> testData = List.of(
            List.of(3,0,3,7,3),
            List.of(2,5,5,1,2),
            List.of(6,5,3,3,2),
            List.of(3,3,5,4,9),
            List.of(3,5,3,9,0)
    );
    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(21, AOCDay8.challenge1(testData));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        Assert.assertEquals(8, AOCDay8.challenge2(testData));
    }
}
