import java.io.*;

public class AddCommit {
    private String file;
    private String directory;

    public AddCommit() {
        this.file = null;
        this.directory = null;
    }

    public AddCommit(String directory) {
        this.file = null;
        this.directory = directory;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean add(String fileName) {
        String fileDirectory = this.directory + "\\" + fileName;
        File addFile = new File(fileDirectory);
        if (addFile.exists()) {
            try {
                FileWriter index = new FileWriter(this.directory + "\\.myGit\\index.txt");
                index.write(fileName + "\n");
                index.close();
            } catch (IOException e) {
            }
            this.setFile(fileName);
            return true;
        }
        System.out.println("fatal: pathspec '" + fileName + "' did not match any files");
        return false;
    }

    public boolean commit(){
        if(file == null)
            return false;
        try {
            FileReader HEAD = new FileReader(this.directory+"\\.myGit\\HEAD.txt");
            BufferedReader bufReader = new BufferedReader(HEAD);
            String version = bufReader.readLine().split(" ")[1];
            version = Integer.toString(Integer.parseInt(version.split("\\.",2)[0])+1)+".0.0";
            FileReader input = new FileReader(this.directory+"\\"+this.file);
            File folder = new File(this.directory+"\\.myGit\\version\\"+version);
            folder.mkdir();
            FileWriter output = new FileWriter(this.directory+"\\.myGit\\version\\"+version+"\\"+this.file);
            int word;
            while((word = input.read()) != -1){
                output.write((char)word);
            }

            bufReader.close();
            HEAD.close();
            input.close();
            output.close();
        }catch (IOException e){
        }
        return true;
    }
}
