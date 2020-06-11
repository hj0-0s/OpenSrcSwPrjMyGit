import java.io.*;

public class Git {
    private AddCommit addCommit;
    private String directory;

    public Git() {
        this.addCommit = null;
        this.directory = null;
    }
    public Git(String directory) {
        this.addCommit = null;
        this.directory = directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void constructor() {
        this.addCommit = new AddCommit(this.directory);
    }

    public boolean init(String directory){
        String gitDirectory = directory + "\\.myGit";
        File file = new File(gitDirectory);
        if (!file.exists()) {
            this.constructor();
            file.mkdir();
            file = new File(gitDirectory + "\\version");
            file.mkdir();
            try {
                FileWriter index = new FileWriter(this.directory + "\\.myGit\\index.txt");
                FileWriter HEAD = new FileWriter(this.directory + "\\.myGit\\HEAD.txt");
                HEAD.write("version 0.0.0\n");
                index.close();
                HEAD.close();
            }catch(IOException e){}
            System.out.println("Initialized empty myGit repository in " + directory + "\\");
            return true;
        } else {
            System.out.println("Reinitialized existing myGit repository in " + directory + "\\");
            return false;
        }
    }

    public void changeDirectory(String directory){
        this.setDirectory(directory);
        String gitDirectory = directory + "\\.myGit";
        File file = new File(gitDirectory);
        if (file.exists()) {
            this.constructor();
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
