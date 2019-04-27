package apppac;

import java.sql.*;
import java.io.FileFilter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Commands {

    @SuppressWarnings("null")

    String getData() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/terminal";
        Connection connection = DriverManager.getConnection(url, "root", "");
        String query7 = "SELECT * FROM `user1`";
        Statement smt = connection.createStatement();
        // PreparedStatement preStat = connection.prepareStatement(query7);

        ResultSet result = smt.executeQuery(query7);

        String UserData = "";
        while (result.next()) {
            UserData = "Name  " + result.getString("name");
            UserData += System.lineSeparator() + "Username  " + result.getString("username");
            UserData += System.lineSeparator() + "Registration number  " + Integer.toString(result.getInt("registration"));
            UserData += System.lineSeparator() + "Password  " + result.getString("password");
            UserData += System.lineSeparator() + "Group name  " + result.getString("groupname");
            UserData += System.lineSeparator() + "Login time  " + result.getString("time") + System.lineSeparator();

        }
        return UserData;
    }

    String ReadPathFile() {
        String path = System.lineSeparator();
        try {
            FileInputStream inputStream = new FileInputStream("PathFile.txt");                    //reading path from textfile
            try (InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8")) {
                int character;
                while ((character = reader.read()) != -1) {
                    path += (char) character;
                }
            }

        } catch (IOException e) {
        }
        path += ">>";
        return path;
    }
    
    
    String readPathFile() {
        String path = "";
        try {
            FileInputStream inputStream = new FileInputStream("PathFile.txt");                    //reading path from textfile
            try (InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8")) {
                int character;
                while ((character = reader.read()) != -1) {
                    path += (char) character;
                }
            }

        } catch (IOException e) {
        }
        path += ">>";
        return path;
    }

    void cd(String string, String path, String AllDir) throws IOException //AllDir is list of all directory present in pwd
    {
        if (string.compareTo("cd ..") == 0 || string.compareTo("cd..") == 0) {
            int x;
            x = path.lastIndexOf("\\");
            path = path.substring(0, x);
            try {
                try (FileWriter writer = new FileWriter("PathFile.txt", false)) {
                    writer.write(path);
                }
            } catch (IOException e) {
            }

        } else if (string.indexOf('\\') == -1) {
            @SuppressWarnings("UnusedAssignment")
            String word = null;
            word = string.substring(string.indexOf(" ") + 1, string.length());

            boolean found = Arrays.asList(AllDir.split("_|_")).contains(word);
            if (!found) {

                JOptionPane.showMessageDialog(null, "File does not exist");

            } else {
                path += "\\";
                path += word;
                System.out.println(path);
                FileWriter writer = null;
                try {
                    writer = new FileWriter("PathFile.txt", false);
                } catch (IOException ex) {
                    Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    writer.write(path);
                } catch (IOException ex) {
                    Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            String word = string.substring(string.indexOf(" ") + 1);
            FileWriter writer = null;
            try {
                writer = new FileWriter("PathFile.txt", false);
            } catch (IOException ex) {
                Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                writer.write(word);
            } catch (IOException ex) {
                Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    //create new file
    void touch(String Path) throws IOException {
        File f = new File(Path);
        boolean createNewFile = f.createNewFile();
        if (!createNewFile) {
            JOptionPane.showMessageDialog(null, "File already exist");
        }
    }

    void Mkdir(String name) {

        File f;
        f = new File(name);
        if (!f.exists()) {
            f.mkdir();
        } else {
            JOptionPane.showMessageDialog(null, "File already exist");
        }
    }

    


    String ls(String path) throws IOException {
        String s, str;

        String ans = "\n";

        File f = new File(path); // current directory                           }

        File[] files = f.listFiles();

        for (File file : files) {
            s = file.getCanonicalPath();
            str = s.substring(s.lastIndexOf("\\") + 1);
            ans += str;
            ans += "      ";

        }

        return ans;
    }

    String LswithOneSpace(String path) throws IOException {
        String s, str;
        String ans = "";
        File f = new File(path); // current directory                           }
        FileFilter directoryFilter = File::isDirectory;
        File[] files = f.listFiles(directoryFilter);
        for (File file : files) {
            s = file.getCanonicalPath();
            str = s.substring(s.lastIndexOf("\\") + 1);
            ans += str;
            ans += "_|_";

        }

        return ans;
    }
}
