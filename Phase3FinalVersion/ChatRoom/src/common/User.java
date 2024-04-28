package common;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Team Project(Project 05) -- User
 * <p>
 * This class define various properties and methods of the user,
 * such as getters and setters, add/remove friends, block/unblock friends,
 * and methods related to direct message
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */

public class User implements Serializable, UserInterface {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String passwd; //password
    private String name;
    private String username;
    private String password;
    private ImageIcon bio;
    private String email;
    /*    private String[] friends;*/
    private List<String> blockedUsers;
    private List<String> inbox;
    private List<String> friends;

    public void setUsername(String username) {
        this.username = username;
    }

    public User() {
    }

    public List<String> getBlockedUsers() {
        return blockedUsers;
    }


    public void setBlockedUsers(List<String> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.blockedUsers = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    public void setUser(String name, String username, String password, String email) {
        this.blockedUsers = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.name = name;
        this.username = username;

        this.email = email;
        this.password = password;

    }

    /*
    public User(String name, String username, ImageIcon bio, String email, String password) {
        this.name = name;
        this.username = username;

        this.email = email;
        this.password = password;

        this.bio = bio;
    }
    */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Icon getBio() {
        return bio;
    }


    public void setBio(ImageIcon bio) {
        this.bio = bio;
        bio.setImage(bio.getImage().getScaledInstance(Properties.PROFILE_PICTURE_WIDE, Properties.PROFILE_PICTURE_HIGH, 1));

    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }


    public String addFriend(String friendUsername) {
        if (!Blocked(friendUsername) && !Friend(friendUsername) && !this.username.equals(friendUsername)) {
            friends.add(friendUsername);
            System.out.println(friendUsername + " has been added to your friends list.");
            return friendUsername + " has been added to your friends list.";
        } else {
            return "Cannot add " + friendUsername + ".";
        }
    }

    public void removeFriend(String friendUsername) {
        friends.remove(friendUsername);
    }

    public String blockUser(String userToBlock) {
        if (Friend(userToBlock)) {
            friends.remove(userToBlock);
        }
        if (!blockedUsers.contains(userToBlock))
            blockedUsers.add(userToBlock);
        System.out.println(userToBlock + " has been blocked.");
        return userToBlock + " has been blocked.";
    }

    public String unblockUser(String userToUnblock) {
        blockedUsers.remove(userToUnblock);
        if (!friends.contains(userToUnblock)) {
            friends.add(userToUnblock);
        }

        System.out.println(userToUnblock + " has been unblocked.");
        return userToUnblock + " has been unblocked.";
    }

    public boolean Friend(String username) {
        return friends.contains(username);
    }

    public boolean Blocked(String username) {
        return blockedUsers.contains(blockedUsers);
    }


    public boolean contains(String[] array, String value) {
        for (String element : array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }


    public boolean isBlockedBy(String otherUser) {

        return this.blockedUsers.contains(otherUser);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
    public boolean login(String password) {
        return (password.equals(this.password));
    }

     */


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", passwd='" + passwd + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bio=" + bio +
                ", email='" + email + '\'' +
                ", blockedUsers=" + blockedUsers +
                ", inbox=" + inbox +
                ", friends=" + friends +
                '}';
    }

}