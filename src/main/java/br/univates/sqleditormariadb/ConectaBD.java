/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.sqleditormariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ala.klein
 */
public class ConectaBD {

    ResultSet resultadoQ = null;

    public void consulta(String query, JTable tabela) throws ClassNotFoundException {
        try {
            ConectaSSH.go();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
    }
}
