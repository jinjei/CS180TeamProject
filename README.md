# CommunityBulletinBoard
Our project is to implement a CommunityBulletinBoard application. In Phase 1, we have implemented the following functionalities: creating new users, logging in existing users, searching and viewing users, and adding and deleting friends. Upcoming features include the ability to upload and save user images and direct messaging (the code for which is already completed). 

**Instructions for running the code:**
1. When running the program for the first time, ensure that "UserFile.txt" and "FriendFile.txt" exist and each contains only one blank line. 
2. Then, run the main function in CommunityBulletinBoard and follow the prompts to complete user registration. 
3. After registration, you can choose different options to perform corresponding functions. 
4. For adding and removing friends, you need to search for the user's name (not username). If there are users with the corresponding name, they will all be displayed, and you can simply enter the name of the user you want to add. The same applies to removeFriend; enter the name of the friend you want to delete. 
5. After the program finishes running, you can see the results of this run in "UserFile.txt" and "FriendFile.txt".



# UserDatabase

This class is responsible for storing information about all users. It is related to other classes in the sense that every time the `main` function of the `CommunityBulletinBoard` class is executed, a `UserDatabase` object is created and user information is loaded from two text files.
Important Notes: Before running the program for the first time, please ensure that the "users.txt" and "friends.txt" files exist and contain only one line of blank space each.

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

# User 

This class is responsible for defining methods related to the user. It takes in user's information such as name, email, username, and bio. Then, it provides features related to managing friends and direct messaging. This class implements the 'New User' interface. 

## Constructor

'public User(String name, String username, String email, String password)': initializes a new instance of the 'User' class and includes user information listed in the parameters. Bio is not included in the parameter but it is set here as well. 

## Setters and Getters 
'public String getName()': Returns the user's name.
'public String getUsername()': Returns the user's username.
'public String getBio()': Returns the user's bio.
'public String[] getFriends()': Returns an array of the user's friends' usernames.
'public void setFriends(String[] friends1)': Replaces the current list of friends with a new array of friends.
'public void updateBio(String newBio)': Updates the user's bio with a new bio string.
'public String getEmail()': Returns the user's email.
'public void setEmail(String email)': Sets the user's email to a new email address.
'public String getPassword()': Returns the user's password.
'public void setPassword(String password)': Sets the user's password to a new password.

## Methods 

- 'public void addFriend(String friendUsername)': Adds a user to the current user's friends list if not already added and not blocked.
- 'public void removeFriend(String friendUsername)': Removes a user from the current user's friends list.
- 'public void blockUser(String userToBlock)': Blocks a user and adds them to the blocked user's list.
- 'public void unblockUser(String userToUnblock)': Unblocks a user by removing them from the blocked user's list.
- 'public boolean Friend(String username)': Checks to see if the given username is in the current user's friends list.
- 'public boolean Blocked(String username)': Checks to see if the given username is in the current user's blocked users list.
- 'public boolean contains(String[] array, String value)': This is a helper method that checks to see if an array contains a specific value.
- 'public void sendMessage(User recipient, String message)': Sends a message from one user to another unless blocked by one of the users. 
- 'public void receiveMessage(String message)': Adds a received message to the user's inbox.
- 'public void readMessages()': Displays all messages in the user's inbox.
- 'private String[] addToArray(String[] array, String value)': Adds a value to an array and then returns a new array with the added value.
- 'private String[] removeFromArray(String[] array, String value)': Removes a value from an array and then returns a new array with the removed value.
- 'public boolean isBlockedBy(User otherUser)': Checks if the current user is blocked by another user.
- 'public boolean login(String password)': Checks if a given password matches the user's password and then does a login attempt.
- 'public String toString()': Returns a string representation of the user, including their name, username, and bio.


# Direct Messaging

We have followed a snapchat style approach for direct messaging, where users can send messages to other users which can be viewed upon recieving but not 
stored in memory. A few methods in user class support this approach. Going forward, we plan to develop a class that implements DirectMessage Interface 
with the use of Network IO to implement more features as described below. 

## DirectMessage Interface
We have created the interface, which will be further implemented in Phase 2 as part of Network IO incorporation. 

-public boolean sendMssg(User sender, User receiver, String message): Sends a message from a user to a user. 

-public boolean deleteMssg(User sender, User deleter, String message): Deletes a message that has already been sent by a user.

-public boolean receiveMssg(User sender, User receiver, String message): Ensures that receiver has received the message.

-public boolean blockMssg(User receiver, String message): Allows sender to block messages from another user. 

-public boolean blockUser(User sender, User block): Allows sender to block a user. 

-public boolean restrictMssg(User sender, User receiver): Restricts messages based on content or user. 

