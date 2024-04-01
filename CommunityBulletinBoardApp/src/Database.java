/**
 * Team Project(Project 05) -- Database
 * <p>
 * Interface for a class that implements direct messaging
 *
 * @author Lab01, Team 4
 * @version Apr 1, 2024
 */
public interface Database {
    public void addUser(User user);
    public void writeUsers();
    public void readUsers();
    public void readFriends();
    public void writeFriends();
    public User[] searchUser(String userName);
    public String viewUser(User[] users);
}