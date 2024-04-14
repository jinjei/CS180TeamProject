# CS180TeamProject
## Run/Test instructions
There are two folders in CommunityBulletinBoard, one is "server", containing all the server-related code; the other is "chatroom", mainly for the client side, including the user fields, various GUI interfaces, etc. 

First, download both folders, and open one with IntelliJ, taking "server" as an example. **After opening the “server”, select: File-Open to open the chatroom project**. If opening chatroom causes the server folder to close automatically on your computer, you need to click IntelliJ’s: Settings-System Settings-Project, and then select "Ask" in “Open project in”, and afterward click Apply. Then reopen chatroom through: Open-New. This time, you'll get a prompt allowing you to **open chatroom in a New Window, achieving the opening of two projects simultaneously**.

## Configuration before running 
Both server and chatroom have a package named util, which contains a **FileUtil** class. Open the code of this class, where there are two strings with file paths. These are the paths to the file that stores user information. For example, mine currently is "/Users/jinjei/IdeaProjects/cs180/project/TeamProject/UserPic/user.txt". **Please change everything before "user.txt" to some path on your computer with read and write permissions, ending up with the format: “xxx/xxx/xxx/user.txt”. Modify all four places (two in chatroom-FileUtil, two in server-FileUtil)**. 

**In the chatroom folder, there's a test that needs to be run once. If there are no errors, go back to the previously modified “xxx/xxx/xxx” location to check if the user.txt file has been generated successfully**. If not, please check if you've modified all four file paths correctly and consistently, and whether the modified location has local read and write permissions.

After completing all the tasks mentioned above, you can now begin running the project. First, open the server folder, find the main package, and **run the AppServer class** inside it to start the server side of the project. Then, switch to the Chatroom folder, locate the UI package, which contains the LoginFrame class with a main function that you can run. This is the first GUI interface for client users to log in or register. From here, you can explore all other features of the project. **Run the main method of LoginFrame**, follow the prompts to register the project's first user, and then log in using the username and password you just entered during registration. The next step is to enable multiple users to log in simultaneously, experiencing multi-threaded access to the server side, sending private messages between two users, and sending messages to all users of the BulletBoardApp. **To create more users**, stay in the Chatroom folder’s LoginFrame, click on Run-Edit Configurations in IntelliJ, and a window titled Run/Debug Configurations will pop up. In the upper left corner, there are several options: Add New Configuration, Remove Configuration, and Copy Configuration. Select "Copy Configuration" to duplicate the current LoginFrame. If you want to enable 'n' number of users to log in simultaneously, copy the LoginFrame 'n' times, with each user using a different LoginFrame.


## Class Introduction
# Client Connect Server Thread

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
