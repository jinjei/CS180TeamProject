import java.util.ArrayList;

public interface Database {
    public User[] searchUser(String userName);
    public String viewUser(User[] users);
}