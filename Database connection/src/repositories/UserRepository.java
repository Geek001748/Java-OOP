package repositories;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.util.LinkedList;

import entities.User;

public class UserRepository {

        private Connection connection;

        public UserRepository(Connection connection) {
            this.connection = connection;
        }

        public void addUser(User user) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (username, age, balance) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setDouble(3, user.getBalance());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    }
                    System.out.println("User added successfully!");
                } else {
                    System.out.println("Failed to add user. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "1234";
      public Connection getConnection() throws SQLException, ClassNotFoundException{
          String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
          try{
              Class.forName("org.postgresql.Driver");
              Connection con = DriverManager.getConnection(JDBC_URL, "postgres", "1234");
              return con;
          } catch (Exception e){
              e.printStackTrace();
              return null;
          }
    }

        public User getUser(int id ){
            Connection con = null;
            try{
            con = getConnection();
            String sql = "SELECT id, username, age, balance FROM users WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("id"),
                            rs.getString("username"),
                            rs.getInt("age"),
                            rs.getDouble("balance"));
                return user;
            }
            }catch(SQLException throwables){
                throwables.printStackTrace();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            finally {
                try{
                    con.close();

                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }
            }
            return null;
        }
    public void updateUser(int id, String username, int age, double balance) throws SQLException {
            try (Connection con = getConnection();
                 PreparedStatement st = con.prepareStatement("UPDATE users SET username = ?, age = ?, balance = ? WHERE id = ?")) {
                if(getUser(id) == null) {
                    System.out.println("Mistake");
                    return;
                }
                User user = getUser(id);

                st.setString(1, username);
                st.setInt(2, age);
                st.setDouble(3, balance);
                st.setInt(4, id);

                int rowsAffected = st.executeUpdate();
                printUpdateResult(rowsAffected);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            catch (NoSuchElementException e) {
                System.out.println("Incorrect id");
        }
    }

//    public void deleteUser (int id )throws SQLException{
//            Connection con = null;
//            try{
//                con = getConnection();
//                String sql = "DELETE FROM users WHERE id = ?";
//                PreparedStatement st = con.prepareStatement(sql);
//                st.setInt(1,id);
//                st.executeUpdate();
//                System.out.println("User was deleted successfully!");
//            }
//            catch(SQLException throwables){
//            throwables.printStackTrace();
//            }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//                if (con != null) {
//                    try {
//                        con.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//    }


//public void deleteUser(int id) throws SQLException {
//    Connection con = null;
//    try {
//        con = getConnection();
//        String sql = "DELETE FROM users WHERE id = ?";
//        PreparedStatement st = con.prepareStatement("DELETE FROM users WHERE id = ?");
//        st.setInt(1, id);
//        try {
//            int rows = st.executeUpdate();
//            if (rows > 0) {
//                System.out.println("User was deleted successfully!");
//            }else{
//                System.out.println("x");
//            }
//
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//        }
//    } catch (SQLException throwables) {
//        throwables.printStackTrace();
//    } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//        if (con != null) {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

public void deleteUser (int id) throws  SQLException{
try{Connection c = getConnection();  Statement st = connection.createStatement();
    int rows = st.executeUpdate(Queries.deleteUser(id));
    if (rows > 0 ){
        System.out.println("deleted successfully");
    }else {
        System.out.println("Something  went wrong");
    }

}catch (Exception e){
    System.out.println(e.getMessage());
}

}

    private void printUpdateResult(int rowsAffected) {
        if (rowsAffected > 0) {
            System.out.println("Update successful! Rows affected: " + rowsAffected);
        } else {
            System.out.println("No records were updated.");
        }
    }


    public void getAllUsers() {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    int age = resultSet.getInt("age");
                    double balance = resultSet.getDouble("balance");

                    System.out.println("ID: " + id + ", Username: " + username + ", Age: " + age + ", Balance: " + balance);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


