package aocday6;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AOCDay6Tests {

    List<String> testData = List.of(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
            "bvwbjplbgvbhsrlpgdmjqwftvncz",
            "nppdvjthqldpwncqszvftbrmjlhg",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
    );
    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(7, AOCDay6.challenge1(testData.get(0)));
        Assert.assertEquals(5, AOCDay6.challenge1(testData.get(1)));
        Assert.assertEquals(6, AOCDay6.challenge1(testData.get(2)));
        Assert.assertEquals(10, AOCDay6.challenge1(testData.get(3)));
        Assert.assertEquals(11, AOCDay6.challenge1(testData.get(4)));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        Assert.assertEquals(19, AOCDay6.challenge2(testData.get(0)));
        Assert.assertEquals(23, AOCDay6.challenge2(testData.get(1)));
        Assert.assertEquals(23, AOCDay6.challenge2(testData.get(2)));
        Assert.assertEquals(29, AOCDay6.challenge2(testData.get(3)));
        Assert.assertEquals(26, AOCDay6.challenge2(testData.get(4)));
    }
}
