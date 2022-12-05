package aocday5;

import aocday4.AOCDay4;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AOCDay5Tests {

    private final List<String> testCrates = List.of(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 "
    );

    private final List<String> testMoves = List.of(
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2"
    );
    @Test
    public void exampleCaseShouldPassChallenge1() {
        Assert.assertEquals("CMZ", AOCDay5.challenge1(testCrates,testMoves));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        Assert.assertEquals("MCD", AOCDay5.challenge2(testCrates, testMoves));
    }
}
