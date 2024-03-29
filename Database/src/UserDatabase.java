import java.io.*;
import java.util.ArrayList;

public class UserDatabase implements Database {
    private String userFile;
    private ArrayList<User> users;

    public UserDatabase(String userFile) {
        this.userFile = userFile;
        this.users = new ArrayList<>();
    }

    public void addUser(User user){
        this.users.add(user);
    }
    public void loadUsersFromFile(String userFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                String username = userInfo[0].trim();
                String bio = userInfo[1].trim();

                //Just for test, can be modified by the form of the constructor of User class
                User user = new User(username, bio);
                users.add(user);

            }
        } catch (IOException e) {
            System.out.println("Error reading user information from file: " + e.getMessage());
        }
    }

    public User[] searchUser(String nameToSearch) {
        ArrayList<User> foundUsers = new ArrayList<>();
        for (User user : this.users) {
            if (user.getName().toLowerCase().contains(nameToSearch.toLowerCase())) {
                foundUsers.add(user);
            }
        }
        return foundUsers.toArray(new User[0]);
    }

    public String viewUser(User[] users) {
        StringBuilder result = new StringBuilder();
        for (User user : users) {
            result.append("Username: ").append(user.getName()).append("\n");
            result.append("Bio: ").append(user.getBio()).append("\n");
            // Add more user information as needed
            result.append("\n");
        }
        return result.toString();
    }
}