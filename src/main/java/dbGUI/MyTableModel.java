package dbGUI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by traitorwtf on 05.04.2017.
 */
public class MyTableModel extends AbstractTableModel{
    private final int NAME_COLUMN = 0;
    private final int SURNAME_COLUMN = 1;
    private final int AGE_COLUMN = 2;
    private final int CITY_COLUMN = 3;

    private ArrayList<User> users;

    String [] columnNames = {"Name", "Surname", "Age", "City"};

    MyTableModel(ArrayList<User> users){
        this.users = users;
    }

    public int getRowCount() {
        return users.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        User tempUser = users.get(rowIndex);
        switch (columnIndex){
            case (NAME_COLUMN): return tempUser.getName();
            case (SURNAME_COLUMN): return tempUser.getSurname();
            case (AGE_COLUMN): return tempUser.getAge();
            case (CITY_COLUMN): return tempUser.getCity();
            default: return tempUser.getAge();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
