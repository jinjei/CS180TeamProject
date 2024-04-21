# CS180TeamProject - Community Bulletin Board

# Project Documentation

## Important Note
Our project features a complex directory structure that is not executable within the Vocareum platform. For proper functionality and to experience all features of our application, it is necessary to run the project locally by downloading the code from our GitHub repository. To get started, you need to clone the repository from GitHub to your local machine.
[Visit our GitHub repository](https://github.com/jinjei/CS180TeamProject.git)
https://github.com/jinjei/CS180TeamProject

## Features Implemented

### User Registration and Authentication
- **User Registration**: Users can register by providing their credentials, which are securely stored for future sessions.
- **Password Protected Login**: Ensures that user logins are secure and authenticated against stored credentials.

### Friend Management
- **Add Friends**: Users can add other users to their friend list.
- **Remove Friends**: Users have the option to remove friends from their friend list.

### User Blocking
- **Block User**: Users can block other users, preventing them from interacting or viewing their information.
- **Unblock User**: Users can unblock previously blocked users.

### Data Persistence
- Ensures that all user data, including registration information, friend lists, and block lists, are maintained between sessions without loss.

### Thread Safety
- **Concurrency HashMap**: We use Concurrency HashMap as the user database to ensure thread safety.

### Messaging System
- **Direct Messaging**: Fully implemented feature allowing users to send private messages to other users.
- **Unique "Send All"**: Users can send messages to all users of the application, similar to public notifications. This feature was implemented as an innovative addition beyond the project requirements.
- **Messages to Offline User**: We have also implemented the functionality to send messages to offline users. These pending messages will be temporarily stored and, once the recipient logs in, they will see the message in their respective window.

  
## Extra Credit Features

### User Profile Customization
- **Profile Picture Upload at Registration**: Users can upload their profile picture during the registration process, enhancing personalization.
- **Display Profile Picture**: Each user's profile picture is displayed in their personal window upon login.
- **Change Profile Picture**: Post-login, users can change their profile picture, adding a layer of ongoing customization to their profile.



## Usage Instructions
To start the application, follow these steps:
1. **Start the Server**: Navigate to the `server` directory and run the `AppServer.java`.
2. **Launch Client Application**: In the `chatroom` directory, execute `UserClientService.java` to open the user interface.
3. **Register/Login**: Use the interface to register a new account or log in with existing credentials.
4. **Explore Features**: Creating mulyiple identical LoginFrame to run multiple users at same time. Utilize the user interface to explore the functionalities such as adding friends, blocking users, and sending messages.

## Conclusion
This project aims to provide a robust and user-friendly platform for secure communication and social interaction among users. The additional features for profile customization and community messaging enhance the overall user experience and functionality of the application.

## Important Note on Testing Strategy

### Network I/O Classes
The following classes are heavily focused on Java network I/O operations and none of them contain constructors., according to the handout, we did not write JUnit Test for these classes. The testing/grading strategy should focus on integration and system-level testing. We assess the functionality of these classes through the overall application behavior during runtime.
- **Server Directory:**
  - `ServerConnectClientThread`
  - `ManageServerConnectClientThread`
  
- **Chatroom Directory:**
  - `UserClientService`
  - `ClientConnectServerThread`
### Other Tests
- `UserTest`：Run the UserTest, and if the tests pass successfully, the following messages will be printed to the console: "blockUser has been blocked.", "blockUser has been unblocked.", "friendUser has been added to your friends list.", "Excellent - Test ran successfully."
- `MessageTest`：Run the MessageTest, and if tests pass successfully, the following messages will be printed to the console: "Excellent - Test ran successfully."

Thank you for understanding the unique testing challenges and considerations for these classes.




# Run/Test instructions
There are two folders in CommunityBulletinBoard, one is "server", containing all the server-related code; the other is "chatroom", mainly for the client side, including the user fields, various GUI interfaces, etc. 

**Step 1:** Download both folders, and open one with IntelliJ, taking "server" as an example. **After opening the “server”, select: File-Open to open the chatroom project**. 

**Step 2:** Open server and chatroom in two separate windows. If opening chatroom causes the server folder to close automatically on your computer, you need to click IntelliJ’s: Settings - System Settings - Project, and then **select "Ask" in “Open project in”**, and afterward click Apply. Then reopen chatroom through: Open - New. This time, you'll get a prompt allowing you to **open chatroom in a New Window, achieving the opening of two projects simultaneously**.

# Configuration before running 
**Step 1:** Change file paths in 4 places. Both server and chatroom have a package named util, which contains a **FileUtil** class. Open the code of this class, where there are two strings with file paths. These are the paths to the file that stores user information. For example, mine currently is "/Users/jinjei/IdeaProjects/cs180/project/TeamProject/UserPic/user.txt". You don't need to pre-create a user.txt in this location of your choice. **Please change everything before "user.txt" to some path on your computer with read and write permissions, ending up with the format: “xxx/xxx/xxx/user.txt”. Modify all four places (two in chatroom-FileUtil, two in server-FileUtil), and make sure these are the same**. 

**Step 2:** Run "test" in the chatroom folder. In the chatroom folder, there's a test that needs to be run once. You need to prepare a png image in advance in a location on your computer where you have access of read and write. Then replace the file path, for example, on my computer in the following path "/ Users/jinjei IdeaProjects cs180 / project/TeamProject/UserPic/helper.png" is called a helper.png image. Note that the size of the picture should not be too large, preferably less than 800*800. Then, run the test class. **If there are no errors, go back to the previously modified “xxx/xxx/xxx” location in step 1 to check if the user.txt file has been generated successfully**. If not, please check if you've modified all four file paths correctly and consistently, and whether the modified location has local read and write permissions.

After completing all the tasks mentioned above, you can now begin running the project. First, open the server folder, find the main package, and **run the AppServer class** inside it to start the server side of the project. Then, switch to the Chatroom folder, locate the UI package, which contains the LoginFrame class with a main function that you can run. This is the first GUI interface for client users to log in or register. From here, you can explore all other features of the project. **Run the main method of LoginFrame**, follow the prompts to register the project's first user, and then log in using the username and password you just entered during registration. The next step is to enable multiple users to log in simultaneously, experiencing multi-threaded access to the server side, sending private messages between two users, and sending messages to all users of the BulletBoardApp. **To create more users**(Concurrency and multithreading), stay in the Chatroom folder’s LoginFrame, click on **Run - Edit Configurations** in IntelliJ, and a window titled Run/Debug Configurations will pop up. In the upper left corner, there are several options: Add New Configuration, Remove Configuration, and Copy Configuration. **Select "Copy Configuration" to duplicate the current LoginFrame**. If you want to enable 'n' number of users to log in simultaneously, copy the LoginFrame 'n' times, with each user using a different LoginFrame.


# Method Tests by Class

All methods & constructors implemented as part of phase 2 have been tested. There are certain methods used to test other methods, or used in preparation for phase 3 which have not been tested as it is not required. The methods in Message Class and User Class have used JUnit tests. They are common to both Chatroom and Server folders. The methods in the remaining classes, primarily were part of either File IO or Network/Server interactions. They are tested through the main method, or being called by other functioning methods that have been called either in the main method or GUI's in UI package. GUI's have not been tested in this submission as not required for phase 2. Below is a description of each class and how its methods/constructors have been tested. 

**Message** The MessageTest classes tests all methods & constructors in the Method Class. This includes:  the 2 Message Constructors, Getters & Setters for the fields String sender, String getter (receiver), String content, String messageType. Finally, the field String sendTime was created purely for testing purposes and not the functionality of the project. Hence it doesn't need to be tested. 

**User** The UserTest classes tests majority of methods & all constructors in the User Class. There are a few more methods in User Class which have been tested using other means that I will describe here. Firstly, UserTest class tests all constructors in User Class and the methods: get/setUsername, getBlockedUsers, setUser, get/set UserId, get/set Password, getName, get/set Friends, addFriend, removeFriend, blockUser, unblockUser, Friend, Blocked, & getEmail. The remaining methods are toString, get/set bio, isBlockedby, contains, & login. For the toString method, if you run loginFrame, then all the current registered users information will be printed to the console in the format: User{userId='null', passwd='null', name='null', username='helper', password='123456', bio=/Users/jinjei/IdeaProjects/cs180/project/TeamProject/UserPic/helper.png, email='null', blockedUsers=[], inbox=null, friends=[]}. For the get/set bio, it is tested in the 'test' class and you will have to use a picture saved onto your computer to test it by entering the location in the filename section. isBlockedBy is tested in the UserInterfaceFrame Class. It can be tested by running loginFrame, if you try to send a message to a user that has blocked you (which can be achieved through block procedures in user interface frame), then a GUI message should appear saying "You have been blocked by this user!". The contains method is called by isBlockedBy and will be tested similar to isBlockedBy. Finally, login method is called in loginFrame Class, and after running loginFrame, if user is successfully able to login then the method is successful. 

**ClientConnectServerThread** The ClientConnectServerThread constructor is called in the checkUser Method in the UserClientService Class. The checkUser method is called in LoginFrame. Thus, to test this constructor, you can create an account, then login, and the message "Login Success" will be visible which confirms the test was successful. The actionByMessageType method is called in the run method of this class. It prints the actions taken to the Console as a means for us to double check certain actions have been performed and hence doesn't need to be tested as it is not part of the project functionality. 

**UserClientService** The checkUser method is tested as described in ClientConnectServerThread. The onlineFriendList method is not used in Phase 2 and has not been called. It will be implemented in Phase 3 and hence is not tested. closedComm method is used for developer testing of GUI and is not a functionality of the project and doesn't need to be tested. The Send method is called in sendAll. sendAll is called in UserInterfaceFrame.java. To test this, create two accounts, send a message from one user to another and if the message is received by the other user then send & sendAll methods are successfull. formatTime is for personal testing & not part of the phase 2 functionality and hence doesn't need testing.


**Properties** This class does not have any methods. 

**ManageServerConnectClientThread** The getOnlineFriends method is not part of phase 2 functionality. getSocketById method is called in sendAll method. sendAll method gets all the sockets from the users and then sends them a message. It can be tested by using "send all" button in the GUI user interface frame. If you open a few accounts, and then write a message in "send all" text box and click "send all" button and the message sends to all registered users (online or offline) then the method is successful. addMessage method together with sendOfflienMessage method work to send an offline user a message that they can see when they log in. addMessage method saves a message in "messageMap" field. sendOfflineMessage method then takes the message from message map and delivers it to the user when they log in. This can be tested by logging in to an account, sending a message to an offline user through the GUI user interface frame. Then, log in as the offline user, and if the pending message has been received then the methods can be deemed successful. getMap method is not required for phase 2 funcitonalities. deleteSocket method is called by actionbyMessageType in ServerConnectClientThread class. It can be tested by clicking the red cancel button at the top left corner of the user interface frame. If "User [username] disconnected!" prints to the console then the method is successful. addThread method is called by actionByMessageType in ServerConnectClientThread class. It can be tested by logging in, and then if the message "User [username] connected with server..." prints to the console then the method can be deemed successful. 

**ServerConnectClientThread** The constructor here is called in AppServer main method. It is supposed to connect the socket related to the current user to the server. It can be tested if the "Login Success" GUI is visible when logging in. The actionByMessageTest is not part of phase 2 functionalities. It is used to test other methods and hence doesn't need to be tested. The getSocket() method is called in getSocketById method in ManageServerConnectClientThread Class. If the testing for getSocketById method is successful then this is successful. 

**FileUtil** This class serves as the write and read method to access our database. Its testing can be read in the "configuration before running" under the "run test instructions" header above. 

**FrameUtil** Frame Util is GUI related and hence not tested as well since it's part of phase 3. 



# Class Introduction

# ClientConnectServerThread

## Overview
The `ClientConnectServerThread` class is responsible for maintaining a continuous connection between the client and the server. It handles incoming messages from the server, processes them based on their type, and updates the user interface accordingly.

## Features
- Continuous listening for messages from the server.
- Processing different types of messages such as online friends list, common messages, and user availability.
- Update the UI with messages received from other users or system notifications.

## How to Use
To use this class, instantiate it with a connected socket and the user ID. Then, start the thread to begin listening for messages:

```java
Socket socket = new Socket("server_address", port_number);
ClientConnectServerThread clientThread = new ClientConnectServerThread(socket, "userId");
clientThread.start();
```


# UserClientService

## Overview
The `UserClientService` class is responsible for managing the communication between the client and the server. It handles everything from user authentication, retrieving online friend lists, sending messages, to closing the communication.

## Features
- User login verification with the server.
- Fetching the list of online users.
- Sending personal and broadcast messages.
- Properly closing the connection when the user exits.

## Usage
To use this service, create an instance of `UserClientService` and use its methods to communicate with the server based on user actions. Ensure the server is running and accessible before making calls to these methods.

## Dependencies
This class relies on:
- `common.User` for user-related data.
- `common.Message` and `common.MessageType` for handling message types.
- Java networking and I/O classes for network communication.



# User

## Overview
The `User` class is part of the Team Project for managing user data and interactions within a network application. It encapsulates all necessary user information and behaviors such as managing friendships, blocking other users, and handling user authentication and messaging functionalities.

## Features
- **User Management**: Handles user information such as username, password, email, and profile picture.
- **Friend Management**: Supports adding and removing friends.
- **Block Management**: Allows users to block and unblock other users.
- **Authentication Support**: Assists in password verification processes (commented out in the example for potential future use).
- **Profile Customization**: Users can update their profile pictures, enhancing personalization.

## Class Methods
- `setUsername(String username)`: Sets the username of the user.
- `setUser(String name, String username, String password, String email)`: Sets the user's full details.
- `addFriend(String friendUsername)`: Adds a new friend if not already added or blocked.
- `removeFriend(String friendUsername)`: Removes a friend from the user's friend list.
- `blockUser(String userToBlock)`: Blocks another user.
- `unblockUser(String userToUnblock)`: Unblocks a previously blocked user.
- `getBio()`: Returns the user's profile picture.
- `setBio(ImageIcon bio)`: Sets the user's profile picture.

## Serialization
This class implements `Serializable`, allowing user instances to be saved and loaded from a file or sent over a network.

## Usage Example
Below is an example of how to create a `User` object, set user details, and manage friendships and blocks:

```java
User user = new User("username", "password");
user.setUser("John Doe", "johndoe", "securepassword123", "johndoe@example.com");
user.addFriend("janedoe");
user.blockUser("foeuser");
```



# Message

## Overview
The `Message` class is part of the common package and is designed to encapsulate all information necessary for messages sent between clients and servers in a networked communication system. This class supports serializable operations, making it suitable for transmission over a network.

## Features
- **Serializable**: Implements `Serializable` to allow object instances to be converted to a stream and sent over the network.
- **Flexibility**: Can be used to transmit various types of messages, such as text messages, system commands, or user status updates.

## Attributes
- **sender**: Identifier for the message sender.
- **getter**: Identifier for the message recipient.
- **content**: The actual content of the message.
- **sendTime**: Timestamp when the message was sent.
- **messageType**: Type of the message, which can define how the message is processed.

## Constructor Details
- `Message()`: Constructs an empty message.
- `Message(String sender, String messageType)`: Constructs a message with specified sender and message type.

## Method Overview
- `getSender()`, `setSender(String)`: Get or set the sender of the message.
- `getGetter()`, `setGetter(String)`: Get or set the receiver of the message.
- `getContent()`, `setContent(String)`: Get or set the content of the message.
- `getSendTime()`, `setSendTime(String)`: Get or set the time the message was sent.
- `getMessageType()`, `setMessageType(String)`: Get or set the type of the message.



# LoginFrame

## Overview
The `LoginFrame` class is the entry point for the chat system application. It provides a graphical user interface for users to log in or register.

## Features
- Graphical login and registration interface.
- User authentication against a server.
- Transition to the main chat interface upon successful login.
- Option to navigate to the registration interface.

## Usage
To start the application, simply run the `main` method in the `LoginFrame` class. This will launch the login window, where users can either log in with existing credentials or navigate to the registration page to create a new account.

## Dependencies
This class depends on several other components:
- `UserClientService`: For handling login authentication with the server.
- `FileUtil`: To retrieve user information.
- `FrameUtil`: For positioning the frame on the screen.
- `RegisterFrame`: For navigating to the registration interface.
- `UserInterfaceFrame`: For transitioning to the main chat interface upon successful login.

## Design
The frame is designed with a simple grid layout that includes:
- Text fields for entering the username and password.
- Buttons for login and registration actions.

## Components
- `JFrame` for the main window.
- `JTextField` for the username input.
- `JPasswordField` for the password input.
- `JButton` for actions.



# BlockListFrame

## Overview
The `BlockListFrame` class provides a graphical user interface (GUI) for managing a user's blocked contacts within a chat application. It allows users to view and remove users from their block list.

## Features
- Display of blocked users with their icons and usernames.
- Capability to remove users from the block list.

## Usage
To use the `BlockListFrame`, instantiate it with a list of all users and the current user object. This frame will then display all users currently blocked by the user, allowing them to remove any user from the block list.

## Dependencies
This class relies on several other components:
- `common.User` for user-related information.
- `util.FileUtil` for saving and retrieving user data.
- `util.FrameUtil` for positioning the frame on the screen.
- `UI.UserInterfaceFrame` for updating the main user interface.

## Design
The frame is designed with:
- A `JTable` to display the list of blocked users.
- A `JButton` to initiate the unblocking process.
- A `CustomModel` for the table that supports non-editable fields except for the checkbox column to select users for unblocking.

## Example of Invocation
```java
ConcurrentHashMap<String, User> users = FileUtil.getUsers();
User currentUser = users.get("username");
new BlockListFrame(users, currentUser);
```



# RegisterFrame

## Overview
The `RegisterFrame` class provides a graphical user interface (GUI) for new users to register in the chat application. It allows users to input their personal information, including a username, password, email, and an optional profile picture.

## Features
- User registration interface with fields for name, username, password, and email.
- Option to upload a profile picture.
- Validation to ensure username uniqueness within the system.
- Immediate feedback on successful registration or errors.

## Usage
To use the `RegisterFrame`, instantiate it with a map of all users. The frame will be displayed, and the user can fill out their information to register.

```java
ConcurrentHashMap<String, User> users = FileUtil.getUsers();
new RegisterFrame(users);
```



# UserInterfaceFrame

## Overview
The `UserInterfaceFrame` class provides the main graphical user interface for the chat application, where users can manage their friends, send messages, block users, and update their profile picture.

## Features
- Display and management of friend lists.
- Functionality to send private and public messages.
- Ability to block other users and manage blocked lists.
- Updating the user's profile picture.
- Real-time chat updates and user status management.

## Usage
To use the `UserInterfaceFrame`, instantiate it using the `getInstance()` method, which ensures that it operates as a singleton. This frame integrates functionalities like sending messages, updating user information, and managing friends and blocks.

## Dependencies
This class depends on several other components:
- `UserClientService`: Handles communication with the server.
- `FileUtil`: Manages reading and writing user data to files.
- `FrameUtil`: Assists in positioning the frame on the screen.
- `common.User`: Represents the user model.

## Design
The frame is organized into several panels:
- The main chat area for interacting with friends.
- A user list that allows for friend management and message sending.
- Control buttons for updating the user profile and managing user settings.



# UserListFrame

## Overview
The `UserListFrame` class provides a graphical interface for managing user interactions within a chat application. It allows users to search, view, and add other users to their friend list.

## Features
- Display of all registered users with their icons and usernames.
- Capability to search for users by name.
- Functionality to add selected users to the current user's friend list.

## Usage
Instantiate the `UserListFrame` with the current user's information and a map of all users. This frame allows the current user to interact with the list of all users for adding friends.



# FileUtil

## Overview
The `FileUtil` class provides essential functionality for reading from and writing to the user database in the form of serialized objects. This utility class is integral for handling persistent storage of user data in the chat application.

## Features
- **Persistent Storage**: Manages the serialization and deserialization of user data to ensure persistence across sessions.
- **Concurrent Access**: Utilizes `ConcurrentHashMap` to ensure thread-safe operations when accessing user data.

## Functions
- `getUsers()`: Retrieves all user data from storage.
- `setUser(ConcurrentHashMap<String, User> users)`: Stores all user data into storage.

## Usage
The methods provided by `FileUtil` are static and can be accessed without instantiating the class. Here's how you can use these methods:

### Reading Users
```java
ConcurrentHashMap<String, User> users = FileUtil.getUsers();
```



# FrameUtil

## Overview
The `FrameUtil` class provides utility functions for managing JFrame properties in Java Swing applications. Its primary functionality is to center frames on the screen, ensuring that windows appear positioned optimally regardless of screen resolution or frame size.

## Features
- **Automatic Centering**: Adjusts the position of a JFrame so that it is centered on the screen.
- **Size Adjustment**: If the JFrame dimensions exceed the screen size, they are adjusted to fit within the screen.

## Function
- `center(JFrame jFrame)`: Centers the given JFrame on the screen. If the JFrame's size exceeds the screen dimensions, it resizes the frame to match the screen size.

## Usage
To utilize the centering functionality, simply pass your JFrame instance to the `center` method. This method adjusts the JFrame's position and size if necessary, ensuring it appears centered and within the screen's bounds.



# AppServer
`AppServer` is the core server-side component of our networked application. It listens on port 9999 and handles incoming client connections continuously. For each client, it authenticates user credentials and, upon successful authentication, starts a dedicated thread (`ServerConnectClientThread`) to manage further communications with the client.

## Features

- **User Authentication**: Validates usernames and passwords to ensure that only registered users can log in.
- **Session Management**: Manages active user sessions through dedicated threads, allowing simultaneous and responsive interactions for multiple users.
- **Offline Messaging**: Temporarily stores messages intended for offline users and delivers them once the user reconnects.



# ManageServerConnectClientThread

## Overview
`ManageServerConnectClientThread` is a central management class in the server module of the Team Project. It handles the mapping of client threads to user IDs, manages sending and storing messages, and tracks online users. This class is critical for the functionality of a networked chat application, ensuring that messages are routed correctly and efficiently.

## Features
- **Connection Management**: Tracks all active server threads for connected clients and their associated user IDs.
- **Message Routing**: Facilitates sending messages to all connected clients or specific users, including support for broadcasting messages to multiple users.
- **Offline Message Handling**: Temporarily stores messages intended for users who are currently offline, delivering them when the user reconnects.
- **Online User Tracking**: Provides a method to retrieve a list of all currently online users.

## Methods
- `getOnlineFriends()`: Returns a string listing all users currently online.
- `getSocketById(String userId)`: Retrieves the `Socket` associated with a given user ID.
- `sendAll(Socket socket, ObjectOutputStream oos, Message message)`: Sends a message to all connected users except the sender.
- `addMessage(String userId, Message message)`: Stores a message intended for a user who is currently offline.
- `sendOffLineMessage(String userId, ObjectOutputStream oos)`: Sends stored messages to a user when they reconnect.
- `deleteSocket(String userId)`: Removes a user and their server thread from the management map.
- `addThread(String userId, ServerConnectClientThread thread)`: Adds a new server thread associated with a user ID to the management map.

## Usage
This class is utilized within the server-side application to manage connections and message flows between clients. It is not instantiated directly but supports the server operations by providing static methods that handle various aspects of connection and message management.



# ServerConnectClientThread

## Overview
`ServerConnectClientThread` is a class within the server module of the Team Project that handles the communication between the server and individual clients. This class is responsible for processing all client-server interactions for a specific client once they are connected.

## Features
- **Connection Handling**: Manages the socket connection for a specific client, ensuring messages are received and sent properly.
- **Message Routing**: Processes incoming messages and takes appropriate actions based on the message type, such as sending user lists, broadcasting messages, and handling direct messages.
- **Session Management**: Maintains the session for a user until they disconnect, and ensures proper cleanup of resources upon disconnection.

## Methods
- `run()`: Continuously listens for messages from the connected client and processes them.
- `actionByMessageType(Message message)`: Determines the action to take based on the type of message received.
- `getSocket()`: Returns the socket associated with the current client.

## Usage
This class is instantiated when a new client connects to the server. It then takes over handling all messages from that client, allowing for asynchronous communication between the server and multiple clients at once.

