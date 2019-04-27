/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apppac;

import java.sql.*;
import javax.swing.JOptionPane;

public class rootCommand extends NewJFrame2 {

    String rChange(String str) {
        if ("root".equalsIgnoreCase(usrname) && "password".equals(passw)) {
            try {
                int i = 8;
                String cgrp = "Normal";
                while (str.charAt(i) != ' ') {
                    i++;
                }
                String unm = str.substring(8, i);
                String passw1 = str.substring(i + 1);
                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/terminal";
                Connection connection = DriverManager.getConnection(url, "root", "");
                String query6 = "SELECT * FROM `user1`";
                PreparedStatement estat = connection.prepareStatement(query6);
                ResultSet result = estat.executeQuery();
                while (result.next()) {
                    String nmU = result.getString("username");
                    String passu = result.getString("password");
                    String Ugp = result.getString("groupname");
                    if (nmU.equals(unm) && passu.equals(passw1)) {
                        if (Ugp.equals("guest")) {
                            cgrp = "normal";
                        } else {
                            cgrp = "guest";
                        }
                        String query10 = "UPDATE `user1` SET `groupname` = '" + cgrp + "' WHERE username= '" + unm + "' AND password='" + passw1 + "'";
                        PreparedStatement preStat = connection.prepareStatement(query10);
                        preStat.executeUpdate();
                        return (System.lineSeparator() + "GROUP OF USER" + unm + "UPDATED SUCCESSFULLY" + System.lineSeparator() + "UPDATED BY ROOOT\t" + tim);

                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "connection failed");
            }
        } else {
            return "TO RUN THIS COMMAND YOU SHOULD BE A ROOT USER";
        }
        return null;
    }

    String nChange(String str) {

        String password = str.substring(str.lastIndexOf(" ") + 1);
        if (password.equals("cgroup")) {
            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/terminal";
                Connection connection = DriverManager.getConnection(url, "root", "");

                if ((NewJFrame2.grup).equals("guest")) {
                    String gfg = "normal";

                    String query5 = "UPDATE `user1` SET `groupname` = '" + gfg + "' WHERE `username` = '" + usrname + "' AND `password` = '" + passw + "'";
                    PreparedStatement preStat1 = connection.prepareStatement(query5);

                    preStat1.executeUpdate();

                } else {
                    String gfd = "guest";
                    String query9 = "UPDATE `user1` SET `groupname` = '" + gfd + "' WHERE `username`= '" + usrname + "' AND `password`='" + passw + "'";
                    PreparedStatement preStat2 = connection.prepareStatement(query9);
                    preStat2.executeUpdate();
                }

                String rs = System.lineSeparator() + usrname + "'s group updated successfully" + System.lineSeparator() + "updated by self at\t" + tim;
                return rs;
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "connection failed");
            }
        } else {
            return System.lineSeparator() + "INCORRECT PASSWORD";
        }
        return System.lineSeparator() + null;
    }

}
