/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.sqleditormariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author ala.klein
 */
public class ConectaBD {

    ResultSet resultadoQ = null;

    public long consulta(String query, JTable tabela) {
        long start = System.nanoTime();

        Connection con = null;
        String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://" + ConectaSSH.rhost + ":" + ConectaSSH.lport + "/";
        String db = "classicmodels";
        String dbUser = "admindb";
        String dbPasswd = "2021A";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, dbUser, dbPasswd);
            try {
                Statement st = con.createStatement();
                resultadoQ = st.executeQuery(query);

                new TableModel().resultSetToTableModel(resultadoQ, tabela);
                con.close();

            } catch (SQLException s) {
                JOptionPane.showMessageDialog(null, "SQL statement is not executed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();

        long time = (end - start) / 1000000;

        return time;
    }
}
