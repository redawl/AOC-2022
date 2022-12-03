package aocday3;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AOCDay3Tests {
    private final List<String> testData = List.of(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
    );

    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(157, AOCDay3.challenge1(testData));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        Assert.assertEquals(70, AOCDay3.challenge2(testData));
    }
}
