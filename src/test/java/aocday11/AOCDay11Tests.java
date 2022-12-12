package aocday11;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AOCDay11Tests {

    List<List<String>> testData = List.of(
            List.of("Monkey 0:",
                    "  Starting items: 79, 98",
                    "  Operation: new = old * 19",
                    "  Test: divisible by 23",
                    "    If true: throw to monkey 2",
                    "    If false: throw to monkey 3"
                    ),
                    List.of("Monkey 1:",
                    "  Starting items: 54, 65, 75, 74",
                    "  Operation: new = old + 6",
                    "  Test: divisible by 19",
                    "    If true: throw to monkey 2",
                    "    If false: throw to monkey 0"
                            ),
                    List.of("Monkey 2:",
                    "  Starting items: 79, 60, 97",
                    "  Operation: new = old * old",
                    "  Test: divisible by 13",
                    "    If true: throw to monkey 1",
                    "    If false: throw to monkey 3"
                            ),
                    List.of("Monkey 3:",
                    "  Starting items: 74",
                    "  Operation: new = old + 3",
                    "  Test: divisible by 17",
                    "    If true: throw to monkey 0",
                    "    If false: throw to monkey 1"
                    )
    );
    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(10605, AOCDay11.challenge1(testData));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        Assert.assertEquals(2713310158L, AOCDay11.challenge2(testData));
    }
}
