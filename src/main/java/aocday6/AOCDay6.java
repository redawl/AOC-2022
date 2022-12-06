package aocday6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

public class AOCDay6 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay6.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();



    public static int challenge1(String inputStream){
        return findUniqueCharacters(inputStream, 4);

    }

    public static int challenge2(String inputStream){
        return findUniqueCharacters(inputStream, 14);
    }

    /**
     * Find the index where the previous characters are all unique, up to a number
     * @param inputStream String to check
     * @param amount Amount of unique characters to search for
     * @return End index of the unique character range
     */
    private static int findUniqueCharacters(String inputStream, int amount){
        for(int i = amount; i < inputStream.length(); i++){
            String substr = inputStream.substring(i - amount, i);

            if(substr.chars().distinct().count() == amount) return i;
        }

        throw new FailedToFindCharacterRangeException(amount);
    }

    public static void main(String [] args){
        String inputStream = fileUtils.getFileContents("challenge6.txt").findFirst()
                .orElseThrow(() -> new RuntimeException("Failure"));

        logger.info("Result: {}", challenge1(inputStream));
        logger.info("Result: {}", challenge2(inputStream));
    }
}
