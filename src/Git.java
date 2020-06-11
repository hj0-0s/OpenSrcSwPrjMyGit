import java.io.*;

public class Git {
    private AddCommit addCommit;
    private String directory;

    public Git() {
        this.addCommit = null;
        this.directory = null;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void constructor() {
        this.addCommit = new AddCommit(directory);
    }

    public boolean init(String directory) throws IOException {
        String gitDirectory = directory + "\\.myGit";
        File file = new File(gitDirectory);
        if (!file.exists()) {
            this.setDirectory(directory);
            this.constructor();
            file.mkdir();
            file = new File(gitDirectory + "\\version");
            file.mkdir();

            FileWriter index = new FileWriter(this.directory + "\\.myGit\\index.txt");
            FileWriter HEAD = new FileWriter(this.directory + "\\.myGit\\HEAD.txt");
            HEAD.write("version 0.0.0\n");
            index.close();
            HEAD.close();
            System.out.println("Initialized empty myGit repository in " + directory + "\\");
            return true;
        } else {
            System.out.println("Reinitialized existing myGit repository in " + directory + "\\");
            return false;
        }
    }

    public boolean add(String fileName) {
        return addCommit.add(fileName);
    }

    public boolean commit() {
        return addCommit.commit();
    }

    public void help() {
        System.out.println("\tinit\t\t\tCreate an empty myGit repository or reinitialize an existing one");
    }
}
