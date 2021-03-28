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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ala.klein
 */
public class ConectaBD {

    ResultSet resultadoQ = null;
    ArrayList<String> cabecalho = new ArrayList();
    ArrayList<String> conteudo = new ArrayList();
    Object[][] dadosTabela = null;

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

//                //consulta o nome das colunas e adiciona ao objeto cabeçalho da tabela
//                ResultSetMetaData metadata = resultadoQ.getMetaData();
//                int columnCount = metadata.getColumnCount();
//                for (int i = 1; i <= columnCount; i++) {
//                    //System.out.println((metadata.getColumnName(i) + ", "));
//                    cabecalho.add(metadata.getColumnName(i));
//                }
//                Object[] objArray = cabecalho.toArray();
//
//                while (resultadoQ.next()) {
//                    String row = "";
//                    for (int i = 1; i <= columnCount; i++) {
//                        row += resultadoQ.getString(i) + ", ";
//                    }
//                    System.out.println();
//                    System.out.println(row);
//                }
//
//                // configuracoes adicionais no componente tabela
//                tabela.setModel(new DefaultTableModel(dadosTabela, objArray) {
//                    @Override
//                    // quando retorno for FALSE, a tabela nao é editavel
//                    public boolean isCellEditable(int row, int column) {
//                        return false;
//                    }
//                });
                tabela.setModel(new TableModel().buildTableModel(resultadoQ));

            } catch (SQLException s) {
                System.out.println("SQL statement is not executed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
