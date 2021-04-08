package br.univates.sqleditormariadb;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author ala.klein
 */
public class TableModel {

    public void resultSetToTableModel(ResultSet rs, JTable table) throws SQLException {
        //Create new table model
        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        tableModel.addColumn("");

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount + 1];

        //Scroll through result set
        int j = 1;
        while (rs.next()) {
            row[0] = j;
            j++;
            //Get object from column with specific index of result set to array of objects
            for (int i = 1; i <= columnCount; i++) {
                row[i] = rs.getObject(i);
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
    }
}
