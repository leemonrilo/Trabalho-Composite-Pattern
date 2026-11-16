import java.util.ArrayList;
import java.util.List;


interface FileSystemComponent {
    void display(String indent);
}

class FileItem implements FileSystemComponent {
    private final String name;

    public FileItem(String name) {
        this.name = name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "[File] " + name);
    }
}

class Folder implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "[Folder] " + name);
        for (FileSystemComponent child : children) {
            child.display(indent + "  ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("Documents");
        Folder projects = new Folder("Projects");

        root.add(new FileItem("notes.txt"));
        root.add(projects);
        
        projects.add(new FileItem("Main.java"));
        projects.add(new FileItem("README.md"));

        System.out.println("File system structure:");
        root.display("");
    }
}