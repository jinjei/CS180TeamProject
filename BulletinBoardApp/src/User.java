import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private String username;
    private String bio;
    private String[] friends = new String[0];
    private String[] blockedUsers = new String[0];
    private List<String> inbox = new ArrayList<>();

    public User(String name, String username, String bio) {
        this.name = name;
        this.username = username;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends1) {
        this.friends = friends1;
    }

    public void updateBio(String newBio) {
        this.bio = newBio;
    }

    public void addFriend(String friendUsername) {
        if (!Blocked(friendUsername) && !Friend(friendUsername)) {
            friends = addToArray(friends, friendUsername);
            System.out.println(friendUsername + " has been added to your friends list.");
        } else {
            System.out.println("Cannot add " + friendUsername + ".");
        }
    }

    public void removeFriend(String friendUsername) {
        friends = removeFromArray(friends, friendUsername);
    }

    public void blockUser(String userToBlock) {
        if (Friend(userToBlock)) {
            friends = removeFromArray(friends, userToBlock);
        }
        blockedUsers = addToArray(blockedUsers, userToBlock);
        System.out.println(userToBlock + " has been blocked.");
    }

    public void unblockUser(String userToUnblock) {
        blockedUsers = removeFromArray(blockedUsers, userToUnblock);
        System.out.println(userToUnblock + " has been unblocked.");
    }

    private boolean Friend(String username) {
        return contains(friends, username);
    }

    private boolean Blocked(String username) {
        return contains(blockedUsers, username);
    }

    private boolean contains(String[] array, String value) {
        for (String element : array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void showFriends() {
        if (friends.length == 0) {
            System.out.println("You have no friends added.");
        } else {
            System.out.print("Friends: ");
            for (String friend : friends) {
                System.out.print(friend + " ");
            }
            System.out.println();
        }
    }

    public void showBlockedUsers() {
        if (blockedUsers.length == 0) {
            System.out.println("You have no blocked users.");
        } else {
            System.out.print("Blocked Users: ");
            for (String blockedUser : blockedUsers) {
                System.out.print(blockedUser + " ");
            }
            System.out.println();
        }
    }

    private String[] addToArray(String[] array, String value) {
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }

    private String[] removeFromArray(String[] array, String value) {
        if (!contains(array, value)) {
            return array;
        }
        String[] newArray = new String[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (!array[i].equals(value)) {
                newArray[j++] = array[i];
            }
        }
        return newArray;
    }

    public void sendMessage(User recipient, String message) {
        if (!isBlockedBy(recipient)) {
            recipient.receiveMessage(this.username + ": " + message);
            System.out.println("Message sent to " + recipient.username + ".");
        } else {
            System.out.println("Cannot send message. You are blocked by " + recipient.username + ".");
        }
    }

    private void receiveMessage(String message) {
        inbox.add(message);
    }

    public void readMessages() {
        if (inbox.isEmpty()) {
            System.out.println("Your inbox is empty.");
        } else {
            System.out.println("Your messages:");
            for (String message : inbox) {
                System.out.println(message);
            }

        }
    }

    private boolean isBlockedBy(User otherUser) {
        for (String blockedUser : otherUser.blockedUsers) {
            if (blockedUser.equals(this.username)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "name: " + name + '/' + "userName: " + username + '/' + "bio: " + bio;
    }
}