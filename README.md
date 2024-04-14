# CS180TeamProject
# Run/Test instructions
There are two folders in CommunityBulletinBoard, one is "server", containing all the server-related code; the other is "chatroom", mainly for the client side, including the user fields, various GUI interfaces, etc. 

**Step 1** Download both folders, and open one with IntelliJ, taking "server" as an example. **After opening the “server”, select: File-Open to open the chatroom project**. 

**Step 2** Open server and chatroom in two separate windows. If opening chatroom causes the server folder to close automatically on your computer, you need to click IntelliJ’s: Settings - System Settings - Project, and then **select "Ask" in “Open project in”**, and afterward click Apply. Then reopen chatroom through: Open - New. This time, you'll get a prompt allowing you to **open chatroom in a New Window, achieving the opening of two projects simultaneously**.

# Configuration before running 
**Step 1** Change file paths in 4 places. Both server and chatroom have a package named util, which contains a **FileUtil** class. Open the code of this class, where there are two strings with file paths. These are the paths to the file that stores user information. For example, mine currently is "/Users/jinjei/IdeaProjects/cs180/project/TeamProject/UserPic/user.txt". You don't need to pre-create a user.txt in this location of your choice. **Please change everything before "user.txt" to some path on your computer with read and write permissions, ending up with the format: “xxx/xxx/xxx/user.txt”. Modify all four places (two in chatroom-FileUtil, two in server-FileUtil), and make sure these are the same.**. 

**Step 2** Run "test" in the chatroom folder. In the chatroom folder, there's a test that needs to be run once. You need to prepare a png image in advance in a location on your computer where you have access of read and write. Then replace the file path, for example, on my computer in the following path "/ Users/jinjei IdeaProjects cs180 / project/TeamProject/UserPic/helper.png" is called a helper.png image. Note that the size of the picture should not be too large, preferably less than 800*800. Then, run the test class. If there are no errors, go back to the previously modified “xxx/xxx/xxx” location to check if the user.txt file has been generated successfully**. If not, please check if you've modified all four file paths correctly and consistently, and whether the modified location has local read and write permissions.

After completing all the tasks mentioned above, you can now begin running the project. First, open the server folder, find the main package, and **run the AppServer class** inside it to start the server side of the project. Then, switch to the Chatroom folder, locate the UI package, which contains the LoginFrame class with a main function that you can run. This is the first GUI interface for client users to log in or register. From here, you can explore all other features of the project. **Run the main method of LoginFrame**, follow the prompts to register the project's first user, and then log in using the username and password you just entered during registration. The next step is to enable multiple users to log in simultaneously, experiencing multi-threaded access to the server side, sending private messages between two users, and sending messages to all users of the BulletBoardApp. **To create more users**(Concurrency and multithreading), stay in the Chatroom folder’s LoginFrame, click on **Run - Edit Configurations** in IntelliJ, and a window titled Run/Debug Configurations will pop up. In the upper left corner, there are several options: Add New Configuration, Remove Configuration, and Copy Configuration. **Select "Copy Configuration" to duplicate the current LoginFrame**. If you want to enable 'n' number of users to log in simultaneously, copy the LoginFrame 'n' times, with each user using a different LoginFrame.



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


