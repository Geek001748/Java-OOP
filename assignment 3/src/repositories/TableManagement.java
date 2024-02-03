package repositories;

import java.sql.SQLException;

public interface TableManagement {
    public void createTable() throws SQLException;
    public void addToTable(Object object) throws SQLException;
    public void updateObjectTable(Object object) throws SQLException;
    public void deleteObjectFromTable(int id) throws SQLException;
    public void getAllObjectsFromTable() throws SQLException;
    public void objectInfo(Object object);
    public boolean getObjectFromTable(int id) throws SQLException;
    public Object getObjectClass(int id) throws SQLException;
}

