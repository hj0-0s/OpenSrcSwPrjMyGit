import java.io.File;
import java.util.Scanner;

public class UI {
    private String directory;
    private Scanner sc;

    public UI(){
        this.sc = new Scanner(System.in);
        this.directory = System.getProperty("user.home");
    }

    public boolean isDirectory(String directory, String nextDir) {
        File dir = new File(directory);
        File[] fileList = dir.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            dir = fileList[i];
            if (dir.isDirectory())
                if (dir.getName().equals(nextDir))
                    return true;
        }
        return false;
    }

    public String changeDirectory(String directory, String nextDir) {
        if (isDirectory(directory, nextDir)) {
            directory += ("\\" + nextDir);
            return directory;
        }
        System.out.println("지정된 경로를 찾을 수 없습니다.");
        return directory;
    }

    public void printUI(){
        String[] command;
        Git git = new Git(this.directory);

        while (true) {
            System.out.print(this.directory + ">");
            command = sc.nextLine().split(" ");
            if (command[0].equals("cd")) {
                try {
                    String nextDir = command[1];
                    for (int i = 2; i < command.length; i++)
                        nextDir += (" " + command[i]);
                    directory = changeDirectory(directory, nextDir);
                    git.changeDirectory(directory);
                } catch (IndexOutOfBoundsException e) {
                    //cd 뒤에 폴더 명이 오지 않는 경우
                }

            } else if (command[0].equals("git")) {
                try {
                    switch (command[1]) {
                        case "init":
                            git.init(directory);
                            break;
                        case "add":
                            try {
                                git.add(command[2]);
                            }catch (IndexOutOfBoundsException e){
                            }
                            break;
                        case "commit":
                            git.commit();
                            break;
                        case "help":
                            git.help();
                            break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    git.help();
                }

            } else if (command[0].equals("exit")) {
                System.exit(0);
            }
        }
    }
}
