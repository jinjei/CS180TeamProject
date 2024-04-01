# CS180TeamProject



UserDatabase Class
The UserDatabase class is responsible for storing information about all users. Its relationship with other classes is that every time the main function of the CommunityBulletinBoard class is executed, a UserDatabase object is created and loads user information from two text files.

Fields
private String userFile: Represents the file used to store basic information about all users (such as name, username, bio, etc).
private String friendsFile: Represents the file used to store the friends corresponding to each user.
Methods
public void readUsers(): Reads user information from the userFile, parses it, and stores it in the private ArrayList<User> users field of the UserDatabase.
public void writeUsers(): Writes the basic information of all users into the userFile. The format is one user per line, with each field of the user separated by semicolons.
public void readFriends(): Reads all friends of each user from the friendsFile and assigns the String[] friends field of that user.
public void writeFriends(): Writes all friends of each user into the friendsFile. The format is one user per line, where the first word is the user itself, followed by all of its friends separated by semicolons.
public User[] searchUser(String nameToSearch): Searches for all users whose name contains nameToSearch as a substring among all users and returns an array of User objects.
public String viewUser(User[] users): Accepts the result returned by searchUser, prints the basic information of all found users.
