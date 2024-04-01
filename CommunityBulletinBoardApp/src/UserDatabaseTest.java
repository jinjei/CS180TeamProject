
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDatabaseTest {

    @Test(timeout = 1000)
    public void testSearchUser() {
        // Create a UserDatabase instance
        UserDatabase userDB = new UserDatabase("users.txt", "friends.txt");

        // Create some dummy users
        User user1 = new User("John Doe", "johndoe", "johndoe@example.com", "password");
        User user2 = new User("Alice Smith", "alicesmith", "alice@example.com", "password123");
        User user3 = new User("Bob Johnson", "bobjohnson", "bob@example.com", "qwerty");

        // Add users to the database
        userDB.addUser(user1);
        userDB.addUser(user2);
        userDB.addUser(user3);

        // Search for users
        User[] searchResult = userDB.searchUser("John");

        // Check if the search result contains the expected user
        assertEquals("John Doe", searchResult[0].getName());
    }

    @Test(timeout = 1000)
    public void testViewUser() {
        // Create a UserDatabase instance
        UserDatabase userDB = new UserDatabase("users.txt", "friends.txt");

        // Create some dummy users
        User user1 = new User("John Doe", "johndoe", "johndoe@example.com", "password");
        User user2 = new User("Alice Smith", "alicesmith", "alice@example.com", "password123");
        User user3 = new User("Bob Johnson", "bobjohnson", "bob@example.com", "qwerty");

        // Add users to the database
        userDB.addUser(user1);
        userDB.addUser(user2);
        userDB.addUser(user3);

        // Search for users
        User[] searchResult = userDB.searchUser("John");

        // View the search result
        String viewResult = userDB.viewUser(searchResult);

        // Check if the view result contains the expected user
        assertEquals("name: John Doe/userName: johndoe/bio: null\nname: Bob Johnson/userName: bobjohnson/bio: null\n", viewResult);
    }
}
