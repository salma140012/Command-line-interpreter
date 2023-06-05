import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

public class Terminal {

    private static File curFile, defaultDic;
    Parser parser;

    Terminal() {
        curFile = new File("C:\\FCI\\3rd year");
        defaultDic = new File("C:\\FCI");
    }
    public void chooseCommandAction() {
   	 Parser p=new Parser();
   	 Scanner sc=new Scanner(System.in);
   	 String arg=sc.nextLine();
   	 System.out.println(arg);
   	 p.parse(arg);
   	 switch(p.getCommandName()) {
   	 case "cd":
   		 System.out.println("I'm here");
   		 cd();
   		 break;
   	case "pwd":
  		 pwd();
  		 break;
   		 
   		 
   		 
   	 }
    }

   

    public void cd() {
        curFile = defaultDic;
    }

    public void cd(String path) {
        File newDir = new File(path);
        curFile = newDir;
    }

    public void ls() {
        String arr[] = curFile.list();
        for (String str : arr)
            System.out.println(str);
    }

    public void ls(String filePath, boolean appendMode) {
        String arr[] = curFile.list();
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(new File(filePath), appendMode));
            for (String str : arr)
                out.println(str);
            out.close();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void cp(String sourcePath, String destinationPath) {
        try {
            File source = new File(sourcePath);
            File destination = new File(destinationPath);
            if (source.isFile())
                Files.copy(source.toPath(), destination.toPath());
            else {
                System.out.println(source.toPath());
                System.out.println("cp takes files as arguments not directories");
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void mv(String sourcePath, String destinationPath) {
        try {
            File source = new File(sourcePath);
            File destination = new File(destinationPath);
            Files.move(source.toPath(), destination.toPath());
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public boolean rm(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            String arr[] = file.list();
            for (String str : arr) {
                File tempFile = new File(filePath + '\\' + str);
                if (tempFile.isFile())
                    tempFile.delete();
            }
            return true;
        }
        return file.delete();
    }

    public boolean rmdir(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            return file.delete();
        }
        return false;
    }

    public boolean mkdir(String path) {
        File file = new File(path);
        return file.mkdir();
    }

    public void cat(String filePath) {
        try {
            File file = new File(filePath);
            Scanner in = new Scanner(file);
            while (in.hasNextLine())
                System.out.println(in.nextLine());
            in.close();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void cat(String file1Path, String file2Path, String file3Path, boolean appendMode) {
        try {
            File file1 = new File(file1Path), file2 = new File(file2Path), file3 = new File(file3Path);
            PrintWriter out = new PrintWriter(new FileOutputStream(file3, appendMode));
            Scanner in = new Scanner(file1);
            while (in.hasNextLine())
                out.println(in.nextLine());
            in = new Scanner(file2);
            while (in.hasNextLine())
                out.println(in.nextLine());
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

   

    public String pwd() {
        return curFile.getPath();
    }

  
    }