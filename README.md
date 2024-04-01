# CS180TeamProject



# UserDatabase

This class is responsible for storing information about all users. It is related to other classes in the sense that every time the `main` function of the `CommunityBulletinBoard` class is executed, a `UserDatabase` object is created and user information is loaded from two text files.

## Fields

- `private String userFile`: Represents the file used to store basic information about all users (such as name, username, bio, etc.).
- `private String friendsFile`: Represents the file used to store friends corresponding to each user.

## Methods

- `public void readUsers()`: Reads user information from `userFile`, parses it, and stores it in the private `ArrayList<User> users` of the `UserDatabase` class.
- `public void writeUsers()`: Writes the basic information of all users to `userFile`. The format is one user per line, with each field of the user separated by a semicolon.
- `public void readFriends()`: Reads all friends of each user from `friendsFile` and assigns the `String[] friends` array of that user.
- `public void writeFriends()`: Writes all friends of each user to `friendsFile`. The format is one user per line, with the first word being the user and the subsequent words being all the friends of that user, separated by semicolons.
- `public User[] searchUser(String nameToSearch)`: Searches for all users whose names contain `nameToSearch` as a substring among all users and returns an array of `User` objects.
- `public String viewUser(User[] users)`: Accepts the result returned by `searchUser` and prints the basic information of all users found.


