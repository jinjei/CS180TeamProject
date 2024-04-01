import java.util.Scanner;

public class CommunityBulletinBoard {
    public static final String USER_FILE = "UserFile.txt";
    public static final String FRIEND_FILE = "FriendFile.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Community Bulletin Board");
        System.out.println("Let's set up your profile.");
        UserDatabase database = new UserDatabase(USER_FILE, FRIEND_FILE);
        database.readUsers();
        database.readFriends();

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Bio: ");
        String bio = scanner.nextLine().trim();

        User currentUser = new User(name, username, bio);
        database.addUser(currentUser);

        System.out.println("Profile created successfully!");

        boolean continueUsingApp = true;

        while (continueUsingApp) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add a friend\n2. Remove a friend\n3. Block a user\n4. Unblock a user\n5. Show friends\n6. Show blocked users\n7. Update bio\n8. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();


            switch (choice) {
                case "1":
                    System.out.print("Enter the username of the friend to add: ");
                    String friendToAdd = scanner.nextLine().trim();
                    User[] result = database.searchUser(friendToAdd);
                    if (result.length == 0) {
                        System.out.println("User not found.");
                    } else {
                        System.out.println(database.viewUser(result));
                        System.out.println("Select the friend to add: ");
                        friendToAdd = scanner.nextLine().trim();
                        currentUser.addFriend(friendToAdd);
                        System.out.println("Friend added successfully.");
                    }
                    break;
                case "2":
                    System.out.print("Enter the username of the friend to remove: ");
                    String friendToRemove = scanner.nextLine().trim();
                    currentUser.removeFriend(friendToRemove);
                    System.out.println("Friend removed successfully.");
                    break;
                case "3":
                    System.out.print("Enter the username of the user to block: ");
                    String userToBlock = scanner.nextLine().trim();
                    currentUser.blockUser(userToBlock);
                    System.out.println("User blocked successfully.");
                    break;
                case "4":
                    System.out.print("Enter the username of the user to unblock: ");
                    String userToUnblock = scanner.nextLine().trim();
                    currentUser.unblockUser(userToUnblock);
                    System.out.println("User unblocked successfully.");
                    break;
                case "5":
                    currentUser.showFriends();
                    break;
                case "6":
                    currentUser.showBlockedUsers();
                    break;
                case "7":
                    System.out.print("Enter your new bio: ");
                    String newBio = scanner.nextLine().trim();
                    currentUser.updateBio(newBio);
                    System.out.println("Bio updated successfully.");
                    break;
                case "8":
                    continueUsingApp = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if (continueUsingApp) {
                System.out.println("Would you like to do anything else? (yes/no)");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("no") || answer.equals("n")) {
                    continueUsingApp = false;

                    System.out.println("Thank you for using the Community Bulletin Board. Goodbye!");
                }
            } else {
                System.out.println("Exiting... Thank you for using the Community Bulletin Board. Goodbye!");
            }

        }
        database.writeUsers();
        database.writeFriends();
        scanner.close();
    }
}
