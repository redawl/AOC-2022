package aocday7;

import java.util.ArrayList;
import java.util.List;

public class Directory implements Element<List<Element>>{

    private List<Element> data;

    private Directory parentDirectory;

    private String name;

    public Directory( Directory parentDirectory, String name) {
        this.data = new ArrayList<>();
        this.parentDirectory = parentDirectory;
        this.name = name;
    }

    public void addData(Element data) {
        this.data.add(data);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(List<Element> data) {
        this.data = data;
    }

    public Directory getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

    @Override
    public List<Element> getData() {
        return data;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Element> ls(){
        return data;
    }

    public Element getData(String elementName){
        return data.stream().filter(element -> element.getName().equals(elementName)).findFirst()
                .orElse(null);
    }
}
