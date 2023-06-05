import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Parser {

    
    private String cmd;
    private String []args=new String[4];
    private Terminal terminal;
 //   File file;

    public Parser() {
        terminal = new Terminal();
    }

    public void printCurrentDirectory() {
        System.out.print(terminal.pwd() + "> ");
    }

    public Boolean parse(String command) {
        String[] words = command.split(" ");
        cmd=words[0];
        if(!cmd.isEmpty()&cmd!=null) {
        for (int i = 1; i < words.length; i++) {
            args[i-1] = words[i];}
            return true;
        }if(args.length==0) {
        	return false;
        }
        File file;
        switch (cmd) {
        case "cd":
        	
            if (args.length> 2) {
                System.out.println("Invalid parameters, cd takes 0 or 1 parameters, Write help for more details");
                return false;
            }
            if (args.length == 1) {
                System.out.println("I take one argument");
                return false;
            }
            if (args.length == 2) {
                if (args[0]==("..")) {
                    String path = terminal.pwd();
                    for (int i = path.length() - 1; i >= 0; i--) {
                        if (path.charAt(i) == '\\') {
                            path = path.substring(0, i);
                           break;
                            
                        }
                    }
                    terminal.cd(path);
                    break;
                   
                }
                file = new File(args[0]);
                if (file.isDirectory()) {
                    terminal.cd(args[0]);
                   break;
                   
                }
                file = new File(terminal.pwd() + '\\' + args[0]);
                if (file.isDirectory()) {
                    terminal.cd(terminal.pwd() + '\\' + args[0]);
                    break;
                    
                }
                System.out.println("Invalid Path");
         
            }
            terminal.cd();
            break;
        case "pwd":
            if (args.length == 3 && (args[0]==(">") ||args[0]==(">>"))) {
                try {
                    boolean flag = false;
                    if (args[0]==(">>")) flag = true;
                    PrintWriter out = new PrintWriter(new FileOutputStream(new File(args[1]), flag));
                    out.println(terminal.pwd());
                    out.close();
                    System.out.println("Done");
                    cmd = "pwd";
                  // args.add(parts.get(2));
                } catch (Exception e) {
                    System.out.println("Error " + e);
                    return false;
                }
                break;
            }
            if (args.length!= 1) {
                System.out.println("Invalid parameters, pwd takes no parameters, Write help for more details");
                return false;
            }
            System.out.println(terminal.pwd());
            cmd = "pwd";
            break;
        case "exit":
            System.exit(0);
            break;
        default:
            System.out.println("Invalid command");
        }
        return true;
        }

    public String getCommandName() {
    	return cmd;
    }
     public String[] getargs() {
    	 return args;
     }
  
}