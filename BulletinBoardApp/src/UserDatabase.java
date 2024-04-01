import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDatabase implements Database {
    private String userFile;
    private String friendsFile;
    private ArrayList<User> users;

    public UserDatabase(String userFile, String friendsFile) {
        this.userFile = userFile;
        this.friendsFile = friendsFile;
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void readUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(";");
                String name = userInfo[0].trim();
                String username = userInfo[1].trim();
                String bio = userInfo[2].trim();

                //Just for test, can be modified by the form of the constructor of User class
                User user = new User(name, username, bio);
                users.add(user);

            }
        } catch (IOException e) {
            System.out.println("Error reading user information from file: " + e.getMessage());
        }
    }

    public void writeUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            for (User user : users) {
                writer.write(user.getName() + ";" + user.getUsername() + ";"
                        + user.getBio() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFriends() {
        try (BufferedReader reader = new BufferedReader(new FileReader(friendsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(";");
                String name = userInfo[0].trim();

                String[] friendsList = Arrays.copyOfRange(userInfo, 1, userInfo.length); // Extract friends list
                for (User user : this.users) {
                    if (user.getName().equals(name)) {
                        user.setFriends(friendsList);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFriends() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(friendsFile))) {
            for (User user : this.users) {
                StringBuilder allFriendsBuilder = new StringBuilder();
                allFriendsBuilder.append(user.getName() + ";");
                String[] friendsList = user.getFriends();
                if (friendsList != null) {
                    int count = friendsList.length;
                    for (int i = 0; i < count; i++) {
                        allFriendsBuilder.append(friendsList[i]);
                        if (i < count - 1) {
                            allFriendsBuilder.append(";");
                        }
                    }
                }
                writer.write(allFriendsBuilder.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            result.append(user.toString());
            result.append("\n");
        }
        return result.toString();
    }


}