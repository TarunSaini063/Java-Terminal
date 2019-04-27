package apppac;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

public class TerminalGUI extends javax.swing.JFrame {
    Commands commands = new Commands();
    public TerminalGUI() {
        initComponents();
        TerminalScreen.append(commands.readPathFile());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TerminalScreen = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TERMINAL");
        setForeground(new java.awt.Color(0, 0, 0));

        TerminalScreen.setBackground(new java.awt.Color(0, 0, 0));
        TerminalScreen.setColumns(20);
        TerminalScreen.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        TerminalScreen.setForeground(new java.awt.Color(0, 153, 0));
        TerminalScreen.setRows(5);
        TerminalScreen.setCaretColor(new java.awt.Color(0, 153, 0));
        TerminalScreen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerminalScreenKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TerminalScreen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1477, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("null")

    private void TerminalScreenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerminalScreenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            String content = null;
            try {
                content = TerminalScreen.getDocument().getText(0, TerminalScreen.getDocument().getLength());
            } catch (BadLocationException ex) {
                Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            int lastLineBreak = content.lastIndexOf('\n');
            try {
                TerminalScreen.getDocument().remove(lastLineBreak, TerminalScreen.getDocument().getLength() - lastLineBreak);
            } catch (BadLocationException ex) {
                Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) //to use enter key as output of a command
        {
            String Priority = NewJFrame2.grup;
            Rm RM = new Rm();
            Zip zip = new Zip();
            rootCommand RootCommand = new rootCommand();
            boolean flag = true;
            Search SEARCH = new Search();
            String path = "";
            hardwareCommands hardwarecommands = new hardwareCommands();
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
            //String title=System.lineSeparator()+path+">>";

            flag = true;
            int end = TerminalScreen.getDocument().getLength();          //to read last line line of terminal screen
            int start = 0;
            try {
                start = Utilities.getRowStart(TerminalScreen, end);
            } catch (BadLocationException ex) {
                Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            while (start == end) //taking last line from terminal screen
            {
                end--;
                try {
                    start = Utilities.getRowStart(TerminalScreen, end);
                } catch (BadLocationException ex) {
                    Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String s = null;
            try {
                s = TerminalScreen.getText(start, end - start);
            } catch (BadLocationException ex) {
                Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            s = s.substring(path.length() + 2);
            if (s.charAt(0) == 'm' && s.charAt(1) == 'v') //move command
            {
                if (Priority.equals("root")) {
                    zip.move(s, path);
                    TerminalScreen.append(commands.ReadPathFile());
                } else {
                    JOptionPane.showMessageDialog(null, "Not allowed");
                    TerminalScreen.append(commands.ReadPathFile());
                }
            } else if (s.charAt(0) == 'c' && s.charAt(1) == 'p') {
                if (s.substring(0, 5).compareTo("cp -r") == 0) {
                    zip.cpdir(s);
                    TerminalScreen.append(commands.ReadPathFile());
                } else {
                    try {
                        zip.cp(path, s);
                    } catch (IOException ex) {
                        Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    TerminalScreen.append(commands.ReadPathFile());
                }
            } else if (s.charAt(0) == 'l' && s.charAt(1) == 's') {
                {
                    try {
                        s = commands.ls(path);
                    } catch (IOException ex) {
                        Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                TerminalScreen.append(s);

                TerminalScreen.append(commands.ReadPathFile());
            } else if (s.charAt(0) == 'c' && s.charAt(1) == 'd') {
                String AllDir = null;
                try {
                    AllDir = commands.LswithOneSpace(path);       //store all directory present in currrent path(here ls is same but has words separated by _|_
                } catch (IOException ex) {
                    Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                {

                    try {
                        commands.cd(s, path, AllDir);
                    } catch (IOException ex) {
                        Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                TerminalScreen.append(commands.ReadPathFile());
            } else if (s.charAt(0) == 'r' && s.charAt(1) == 'm') //all rm commands
            {
                if (Priority.equals("root")) {
                    RM.rm(path, s);
                    TerminalScreen.append(commands.ReadPathFile());
                } else {
                    JOptionPane.showMessageDialog(null, "Not allowed");
                    TerminalScreen.append(commands.ReadPathFile());
                }
            } else if (s.length() >= 7 && s.substring(0, 7).equals("getData")) {
                if (Priority.equals("root")) {
                    String Data = null;
                    try {
                        Data = commands.getData();
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    TerminalScreen.append(Data);
                } else {
                    JOptionPane.showMessageDialog(null, "Not allowed");
                    TerminalScreen.append(commands.ReadPathFile());
                }
            } else if (s.length() >= 7 && s.charAt(0) == 'r' && s.charAt(1) == 'C' && s.charAt(2) == 'h' && s.charAt(3) == 'a' && s.charAt(4) == 'n' && s.charAt(5) == 'g' && s.charAt(6) == 'e') {
                if (Priority.equals("root")) {
                    String Status = RootCommand.rChange(s);
                    TerminalScreen.append(Status + System.lineSeparator());
                    TerminalScreen.append(commands.ReadPathFile());
                } else {
                    JOptionPane.showMessageDialog(null, "Not allowed");
                    TerminalScreen.append(commands.ReadPathFile());
                }
            } else if (s.length() >= 7 && s.charAt(0) == 'n' && s.charAt(1) == 'C' && s.charAt(2) == 'h' && s.charAt(3) == 'a' && s.charAt(4) == 'n' && s.charAt(5) == 'g' && s.charAt(6) == 'e') {
                String Status = RootCommand.nChange(s);
                TerminalScreen.append(Status + System.lineSeparator());
                TerminalScreen.append(commands.ReadPathFile());
            } else if (s.charAt(0) == 'z' && s.charAt(1) == 'i' && s.charAt(2) == 'p') {
                try {
                    zip.zip(s, path);
                    TerminalScreen.append(commands.ReadPathFile());
                } catch (IOException ex) {
                    Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                    TerminalScreen.append(commands.ReadPathFile());
                }
            } else if (s.charAt(0) == 'f' && s.charAt(1) == 'i' && s.charAt(2) == 'n' && s.charAt(3) == 'd') //search commands
            {
                String word = s.substring(5);
                boolean result = SEARCH.search(path, word);
                if (!result) {
                    TerminalScreen.append("\n" + "file not found");
                    TerminalScreen.append(commands.ReadPathFile());
                }
                else
                {
                    TerminalScreen.append("\n" + "file found");
                    TerminalScreen.append(commands.ReadPathFile());
                }
            } else if (s.length() >= 4 && s.charAt(0) == 't' && s.charAt(1) == 'o' && s.charAt(2) == 'u' && s.charAt(3) == 'c' && s.charAt(4) == 'h') //touch command
            {
                if (!Priority.equals("guest")) {
                    String name = s.substring(6, s.length());
                    path += "\\";
                    path += name;
                    try {
                        commands.touch(path);
                    } catch (IOException ex) {
                        Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    TerminalScreen.append(commands.ReadPathFile());
                } else {
                    JOptionPane.showMessageDialog(null, "Not allowed");
                    TerminalScreen.append(commands.ReadPathFile());
                }

            } else if (s.length() > 5 && s.charAt(0) == 'u' && s.charAt(1) == 'n' && s.charAt(2) == 'z' && s.charAt(3) == 'i' && s.charAt(4) == 'p') {
                if (!Priority.equals("guest")) {
                    try {
                        zip.Unzip(s, path);
                    } catch (IOException ex) {
                        Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    TerminalScreen.append(commands.ReadPathFile());
                } else {
                    JOptionPane.showMessageDialog(null, "Not allowed");
                    TerminalScreen.append(commands.ReadPathFile());
                }

            } //mkdir command
            else if (s.length() > 5 && s.charAt(0) == 'm' && s.charAt(1) == 'k' && s.charAt(2) == 'd' && s.charAt(3) == 'i' && s.charAt(4) == 'r') {

                String name = s.substring(6, s.length());
                path += "\\";
                path += name;
                commands.Mkdir(path);  //creating new directory
                TerminalScreen.append(commands.ReadPathFile());
            } //check cd commands
            else {
                switch (s) {

                    case "uname":
                        String os = System.lineSeparator() + hardwarecommands.getOS();
                        TerminalScreen.append(os);
                        TerminalScreen.append(commands.ReadPathFile());
                        break;

                    case "systeminfo":
                        if (Priority.equals("root")) {
                            String info1 = hardwarecommands.hrdInfo();
                            info1 += System.lineSeparator() + hardwarecommands.osinfo();
                            TerminalScreen.append(info1);
                            TerminalScreen.append(commands.ReadPathFile());
                        } else {
                            JOptionPane.showMessageDialog(null, "Not allowed");
                            TerminalScreen.append(commands.ReadPathFile());
                        }
                        break;

                    case "ipconfig":

                        if (Priority.equals("root")) 
                            {
                                String ipaddress = System.lineSeparator();
                                try {
                                    ipaddress += hardwarecommands.ipconfig();
                                } catch (UnknownHostException ex) {
                                    Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                TerminalScreen.append(ipaddress);
                                TerminalScreen.append(commands.ReadPathFile());
                            }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Not allowed");
                            TerminalScreen.append(commands.ReadPathFile());
                        }
                        break;

                    case "help":
                        String Help = System.lineSeparator();
                        try {
                            FileInputStream inputStream = new FileInputStream("help.txt");                    //reading path from file
                            try (InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8")) {
                                int character;
                                while ((character = reader.read()) != -1) {
                                    Help += (char) character;
                                }
                            }

                        } catch (IOException e) {
                        }
                        TerminalScreen.append(Help);
                        TerminalScreen.append(commands.ReadPathFile());
                        break;

                    case "clear":
                        TerminalScreen.setText("");  //setText to pointer to next line seteditable( don't know but we can use use it in code)
                       TerminalScreen.append(commands.readPathFile());
                        break;

                    case "pwd":
                        TerminalScreen.append("\n" + path);
                        TerminalScreen.append(commands.ReadPathFile());
                        break;

                    case "exit":
                        FileWriter writer = null;
                        try {
                            writer = new FileWriter("PathFile.txt", false);
                        } catch (IOException ex) {
                            Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         {
                            try {
                                writer.write("C:\\Users\\TARUN\\Documents\\NetBeansProjects\\JavaApplication1");
                            } catch (IOException ex) {
                                Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                         {
                            try {
                                writer.close();
                            } catch (IOException ex) {
                                Logger.getLogger(TerminalGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.exit(0);
                        break;

                    default:
                        TerminalScreen.append(System.lineSeparator() + "Command not found");
                        TerminalScreen.append(commands.ReadPathFile());

                }

            }
        }
    }//GEN-LAST:event_TerminalScreenKeyPressed

    public static void main(String args[]) {

        // TerminalGUI obj =new TerminalGUI(PATHTOPRINT);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TerminalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new TerminalGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TerminalScreen;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
