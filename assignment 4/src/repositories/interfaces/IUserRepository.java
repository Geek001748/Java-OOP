package repositories.interfaces;

import entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
    void addUser(User user) throws SQLException;
    boolean getUser(int id) throws SQLException;
    int getUserTicketAmount(int userId) throws SQLException;
    User getUserClass(int id) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}