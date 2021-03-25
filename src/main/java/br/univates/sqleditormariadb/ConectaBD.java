/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.sqleditormariadb;

import static br.univates.sqleditormariadb.ConectaSSH.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author ala.klein
 */
public class ConectaBD {
    ResultSet resultadoQ = null;
    public void consulta() {
        try {
            go();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Connection con = null;
        String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://" + rhost + ":" + lport + "/";
        String db = "classicmodels";
        String dbUser = "admindb";
        String dbPasswd = "2021A";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, dbUser, dbPasswd);
            try {
                Statement st = con.createStatement();
                String sql = "SELECT * FROM customers";

                resultadoQ = st.executeQuery(sql);

                while (resultadoQ.next()) {

                    System.out.println(resultadoQ.getString("customerName"));
                }

            } catch (SQLException s) {
                System.out.println("SQL statement is not executed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
