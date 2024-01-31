package repositories;

public class Queries {
    public static String  deleteUser(int id){
 return "DELETE FROM users WHERE id = "+id;
    }
}
