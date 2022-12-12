package aocday7;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AOCDay7Tests {

    List<String> testData = List.of(
            "$ cd /",
                    "$ ls",
                    "dir a",
                    "14848514 b.txt",
                    "8504156 c.dat",
                    "dir d",
                    "$ cd a",
                    "$ ls",
                    "dir e",
                    "29116 f",
                    "2557 g",
                    "62596 h.lst",
                    "$ cd e",
                    "$ ls",
                    "584 i",
                    "$ cd ..",
                    "$ cd ..",
                    "$ cd d",
                    "$ ls",
                    "4060174 j",
                    "8033020 d.log",
                    "5626152 d.ext",
                    "7214296 k"

    );
    @Test
    public void exampleCaseShouldPassChallenge1(){
        Assert.assertEquals(95437, AOCDay7.challenge1(testData));
    }

    @Test
    public void exampleCaseShouldPassChallenge2(){
        Assert.assertEquals(24933642, AOCDay7.challenge2(testData));
    }
}
