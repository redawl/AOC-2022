package aocday12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AOCDay12 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay12.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    public static int challenge1(List<String> map){
        // TODO: Implement
        return 0;
    }

    public static void challenge2(){
        // TODO: Implement
    }

    public static void main(String [] args){
        // TODO: Set up initial state
        List<String> map = fileUtils.getFileContents("challenge12.txt")
                        .collect(Collectors.toList());

        logger.info("Result: {}", challenge1(map));
        challenge2();
    }
}
