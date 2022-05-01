/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import domain.Librarian;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LORA
 */
public class TableModelLibrarians extends AbstractTableModel{

    List<Librarian> activeLibrarians;
    private final String[] columnNames=new String[]{"Ime", "Prezime", "Korisničko  ime"};

    public TableModelLibrarians() {
        activeLibrarians = new ArrayList<>();
    }

    public TableModelLibrarians(List<Librarian> activeLibrarians) {
        this.activeLibrarians = activeLibrarians;
    }

    
    
    @Override
    public int getRowCount() {
        return activeLibrarians.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Librarian lib=activeLibrarians.get(rowIndex);
        switch(columnIndex){
            case 0: return lib.getFirstName();
            case 1: return lib.getLastName();
            case 2: return lib.getUsername();
            default: return "n/a";
        }
    }

    public void setActiveLibrarians(List<Librarian> activeLibrarians) {
        this.activeLibrarians = activeLibrarians;
    }
    
    
}
