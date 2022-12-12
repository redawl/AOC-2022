package aocday7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AOCDay7 {
    private static final Logger logger = LoggerFactory.getLogger(AOCDay7.class);

    private static final FileUtils fileUtils = FileUtils.getFileUtils();

    private static final Pattern commandPattern = Pattern.compile("(^\\d+|^\\$ cd|dir)(.*)");

    public static int challenge1(List<String> output){
        Directory topLevel = buildDirectoryHierarchy(output);

        List<Integer> smallDirs = new ArrayList<>();

        findSmallDirs(topLevel, smallDirs);

        return smallDirs.stream().reduce(Integer::sum).orElseThrow(RuntimeException::new);
    }

    private static Directory buildDirectoryHierarchy(List<String> commands) {
        Directory topLevel = new Directory(null, "/");

        Directory currentDirectory = topLevel;
        for (String line : commands) {
            Matcher commandMatcher = commandPattern.matcher(line);
            if (commandMatcher.find()) {
                String command = commandMatcher.group(1).trim();
                String parameter = commandMatcher.group(2).trim();

                if ("$ cd".equals(command)) {
                    if ("..".equals(parameter)) {
                        currentDirectory = currentDirectory.getParentDirectory();
                    } else if ("/".equals(parameter)) {
                        currentDirectory = topLevel;
                    } else {
                        currentDirectory = (Directory) currentDirectory.getData(parameter);
                    }
                } else {
                    if (currentDirectory.getData(parameter) == null) {
                        if ("dir".equals(command)) {
                            currentDirectory.addData(new Directory(currentDirectory, parameter));
                        } else {
                            currentDirectory.addData(new File(Integer.parseInt(command), parameter));
                        }
                    }
                }
            }
        }

        return topLevel;
    }

    private static int findSmallDirs(Directory node, List<Integer> smallDirs){
        AtomicInteger subDirTotal = new AtomicInteger();

        AtomicInteger currentDirTotal = new AtomicInteger();
        node.getData().forEach(child -> {
            if(child instanceof Directory){
                subDirTotal.addAndGet(findSmallDirs((Directory) child, smallDirs));
            } else {
                int fileSize = ((File) child).getData();
                currentDirTotal.addAndGet(fileSize);
            }
        });

        int currentDir = subDirTotal.get() + currentDirTotal.get();

        if(currentDir < 100000) smallDirs.add(currentDir);

        return currentDir;
    }

    public static int challenge2(List<String> output) {
        // TODO: Implement
        Directory topLevel = buildDirectoryHierarchy(output);

        int totalDiskSpace = 70000000;

        int totalUsedSpace = getTotalUsedSpace(topLevel);

        int totalNeededSpace = 30000000 - (totalDiskSpace - totalUsedSpace);

        List<Integer> allDirSizes = new ArrayList<>();

        findAllDirSizes(topLevel,allDirSizes);

        return allDirSizes.stream().filter(dirSize -> dirSize >= totalNeededSpace).min(Integer::compare)
                .orElseThrow(RuntimeException::new);
    }

    private static int findAllDirSizes(Directory node, List<Integer> dirSizes){
        AtomicInteger subDirTotal = new AtomicInteger();

        AtomicInteger currentDirTotal = new AtomicInteger();
        node.getData().forEach(child -> {
            if(child instanceof Directory){
                subDirTotal.addAndGet(findAllDirSizes((Directory) child, dirSizes));
            } else {
                int fileSize = ((File) child).getData();
                currentDirTotal.addAndGet(fileSize);
            }
        });

        int currentDir = subDirTotal.get() + currentDirTotal.get();

        dirSizes.add(currentDir);

        return currentDir;
    }

    private static int getTotalUsedSpace(Directory node){
        AtomicInteger total = new AtomicInteger();

        node.getData().forEach(child -> {
            if(child instanceof Directory){
                total.addAndGet(getTotalUsedSpace((Directory) child));
            } else {
                int fileSize = ((File) child).getData();
                total.addAndGet(fileSize);
            }
        });

        return total.get();
    }

    public static void main(String [] args){
        List<String> output = fileUtils.getFileContents("challenge7.txt")
                        .collect(Collectors.toList());

        logger.info("Result: {}", challenge1(output));
        logger.info("Result: {}", challenge2(output));
    }
}
