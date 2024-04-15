import common.User;
import util.FileUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.concurrent.ConcurrentHashMap;

public class test {
    public static void main(String[] args) {
        User user = new User("helper","123456");
        user.setBio(new ImageIcon("/Users/jinjei/IdeaProjects/cs180/project/TeamProject/UserPic/helper.png"));
        ConcurrentHashMap<String,User> users = new ConcurrentHashMap<>();
        users.put(user.getUsername(),user);
        //users.add(user2);
        FileUtil.setUser(users);

    }
}
