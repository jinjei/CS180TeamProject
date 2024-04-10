import common.User;
import util.FileUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class test {
    public static void main(String[] args) {
        User user = new User("jianzibei","123456");
        user.setBio(new ImageIcon("/Users/jinjei/IdeaProjects/cs180/project/TeamProject/UserPic/touxiang2.png"));
        ConcurrentHashMap<String,User> users = new ConcurrentHashMap<>();
        users.put(user.getUsername(),user);
        FileUtil.setUser(users);
    }
}
