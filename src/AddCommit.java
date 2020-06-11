import java.io.*;

public class AddCommit {
    private String file;
    private String directory;

    public AddCommit() {
        this.file = null;
        this.directory = null;
    }

    public AddCommit(String directory) {
        this.directory = directory;
        try {
            FileReader index = new FileReader(this.directory + "\\.myGit\\index.txt");
            BufferedReader bufReader = new BufferedReader(index);
            String addFile = bufReader.readLine();
            this.file=addFile;
        }catch(IOException e) {
            this.file = null;
        }
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
            FileReader HEADReader = new FileReader(this.directory+"\\.myGit\\HEAD.txt");
            BufferedReader bufReader = new BufferedReader(HEADReader);
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
            this.file = null;
            bufReader.close();
            HEADReader.close();
            input.close();
            output.close();
            FileWriter HEADWriter = new FileWriter(this.directory+"\\.myGit\\HEAD.txt");
            HEADWriter.write("version "+version+"\n");
            HEADWriter.close();
            FileWriter indexWriter = new FileWriter(this.directory+"\\.myGit\\index.txt");
            indexWriter.write("");
            indexWriter.close();
        }catch (IOException e){
        }
        return true;
    }
}
