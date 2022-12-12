package aocday7;

public class File implements Element<Integer>{

    private final Integer fileSize;

    private final String name;

    public File(Integer fileSize, String name) {
        this.fileSize = fileSize;
        this.name = name;
    }

    @Override
    public Integer getData() {
        return fileSize;
    }

    @Override
    public String getName() {
        return name;
    }
}
