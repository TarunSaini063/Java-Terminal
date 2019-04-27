package apppac;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

public class NewJFrame2 extends javax.swing.JFrame {

    public static String usrname = "";
    public static String passw = "";
    public static String tim = "";
    public static String grup = "";

    public NewJFrame2() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Screen");
        setPreferredSize(new java.awt.Dimension(610, 436));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("login");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Login into your acccount");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setText("User Name");

        jLabel3.setText("Password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            usrname = jTextField1.getText();
            passw = jPasswordField1.getText();
            tim = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            System.out.println("time" + tim);
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/terminal";
            Connection connection = DriverManager.getConnection(url, "root", "");

            String query3 = "SELECT * FROM user1";
            PreparedStatement preStat = connection.prepareStatement(query3);

            ResultSet result = preStat.executeQuery();
            System.out.println("2");
            int count = 0;

            while (result.next()) {
                String st = result.getString("name");
                String st2 = result.getString("username");
                int rs = result.getInt("registration");
                String gup = result.getString("groupname");
                String ps = result.getString("password");
                System.out.println(st2 + "  " + ps);
                if ((st2.equals(usrname)) && (ps.equals(passw))) {
                    ++count;
                    //System.out.println("checking "+usrname+"password "+passw);
                    // String query2 = "DELETE FROM `user1` WHERE `username` = 'usrname'";
                    // PreparedStatement eStat = connection.prepareStatement(query2);

                    //eStat.executeUpdate();
                    String query2 = "UPDATE `user1` SET `time` = '" + tim + "' WHERE `username` = '" + usrname + "' AND `password` = '" + passw + "'";
                    PreparedStatement eStat = connection.prepareStatement(query2);

                    eStat = connection.prepareStatement(query2);
                    eStat.executeUpdate();
                    grup = result.getString("groupname");
                }
            }

            if (count == 1) {
                System.out.println("logged in successfully " + grup);

                new TerminalGUI().setVisible(true);

                this.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "incorrect user name or password");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("connection unsuccessfull");

        } // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        try {
            usrname = jTextField1.getText();
            passw = jPasswordField1.getText();
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/terminal";
            Connection connection = DriverManager.getConnection(url, "root", "");

            Statement stmt = connection.createStatement();
            String query2 = "SELECT * FROM user";
            PreparedStatement preStat = connection.prepareStatement(query2);
            ResultSet result = preStat.executeQuery();
            System.out.println("2");
            int count = 0;

            System.out.println(usrname + " " + passw);
            while (result.next()) {
                String st = result.getString("name");
                String st2 = result.getString("username");
                int rs = result.getInt("regno");
                String ps = result.getString("password");
                System.out.println(st2 + "  " + ps);
                if ((st2.equals(usrname)) && (ps.equals(passw))) {
                    ++count;
                }
            }

            if (count == 1) {
                System.out.println("logged in successfully ");
                new TerminalGUI().setVisible(true);
                this.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "incorrect user name or password");

            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("connection unsuccessfull");

        } // TODO add your handling code here:

    }//GEN-LAST:event_formKeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                usrname = jTextField1.getText();
                passw = jPasswordField1.getText();
                tim = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                System.out.println("time" + tim);
                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/terminal";
                Connection connection = DriverManager.getConnection(url, "root", "");

                String query3 = "SELECT * FROM user1";
                PreparedStatement preStat = connection.prepareStatement(query3);

                ResultSet result = preStat.executeQuery();
                System.out.println("2");
                int count = 0;

                while (result.next()) {
                    String st = result.getString("name");
                    String st2 = result.getString("username");
                    int rs = result.getInt("registration");
                    String gup = result.getString("groupname");
                    String ps = result.getString("password");
                    System.out.println(st2 + "  " + ps);
                    if ((st2.equals(usrname)) && (ps.equals(passw))) {
                        ++count;
                        //System.out.println("checking "+usrname+"password "+passw);
                        // String query2 = "DELETE FROM `user1` WHERE `username` = 'usrname'";
                        // PreparedStatement eStat = connection.prepareStatement(query2);

                        //eStat.executeUpdate();
                        String query2 = "UPDATE `user1` SET `time` = '" + tim + "' WHERE `username` = '" + usrname + "' AND `password` = '" + passw + "'";
                        PreparedStatement eStat = connection.prepareStatement(query2);

                        eStat = connection.prepareStatement(query2);
                        eStat.executeUpdate();
                        grup = result.getString("groupname");
                    }
                }

                if (count == 1) {
                    System.out.println("logged in successfully " + grup);

                    new TerminalGUI().setVisible(true);

                    this.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(null, "incorrect user name or password");
                }

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("connection unsuccessfull");

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1KeyPressed

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new NewJFrame2().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
