package util;

import common.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Team Project(Project 05) -- FileUtil
 * <p>
 * This class Interact with the user database to read and store user information
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
public class FileUtil {

    public static ConcurrentHashMap<String, User> getUsers() {
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream("/Users/jinjei/IdeaProjects/cs180/project/TeamProject/UserPic/user.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            return (ConcurrentHashMap<String, User>) in.readObject();
        } catch (Exception e) {
            return null;
        }

    }

    public static int setUser(ConcurrentHashMap<String, User> users) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("/Users/jinjei/IdeaProjects/cs180/project/TeamProject/UserPic/user.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
        } catch (Exception e) {
            return 0;
        }
        return 1;

    }

}