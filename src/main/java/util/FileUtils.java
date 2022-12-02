package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final FileUtils fileUtils = new FileUtils();

    private final HashMap<String, Stream<String>> files = new HashMap<>();

    public static FileUtils getFileUtils(){
        return fileUtils;
    }

    public Stream<String> getFileContents(String fileName){
        return getFileContents(fileName, "\n");
    }

    /**
     * Retrieve the contents of a file, and return it as a stream
     * @param fileName Name of file to retrieve
     * @return  Stream of each line of the file
     */
    public Stream<String> getFileContents(String fileName, String regex) {
        if(files.containsKey(fileName)){
            return files.get(fileName);
        }

        try {
            InputStream input = FileUtils.class.getResourceAsStream("/" + fileName);

            if(input == null){
                throw new IOException();
            }

            Stream<String> fileContents = Arrays.stream(new String(input.readAllBytes()).split(regex));

            input.close();
            files.put(fileName, fileContents);

            return fileContents;
        } catch (IOException ex){
            logger.error("No file named {} found!", fileName);
            System.exit(1);
        }
        // Unreachable code. Not sure why intellij is angry
        return null;
    }
}
