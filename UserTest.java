package common;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@RunWith(Enclosed.class)
public class UserTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {

        @Test(timeout = 1000)
        public void UserClassTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = User.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `User` is `public`!",
                    Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `User` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `User` extends `Object`!",
                    Object.class, superclass);
            Assert.assertEquals("Ensure that `User` implements interfaces!",
                    2, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void UserConstructorTest() {

            String username = "j123";
            String password = "password1";

            User u = new User(username,password);

            Assert.assertTrue("Ensure that `User` username is j123!",
                    (u.getUsername().equals("j123")));

            Assert.assertTrue("Ensure that `User` password is password1!",
                    (u.getPassword().equals("password1")));

        }

        @Test(timeout = 1000)
        public void setUserTest() {

            String name = "John";
            String username = "j123";
            String email = "j@gmail.com";
            String password = "password1";
            String bio = "hi";

            User u = new User();

            u.setUser(name, username, password, email);


            Assert.assertTrue("Ensure that `User` name is John!",
                    (u.getName().equals("John")));

            Assert.assertTrue("Ensure that `User` username is j123!",
                    (u.getUsername().equals("j123")));

            Assert.assertTrue("Ensure that `User` password is password1!",
                    (u.getPassword().equals("password1")));

            Assert.assertTrue("Ensure that `User` email is j@gmail.com!",
                    (u.getEmail().equals("j@gmail.com")));
        }

        @Test(timeout = 1000)
        public void userConstructorTwoTest() {

            String name = "John";
            String username = "j123";
            String email = "j@gmail.com";
            String password = "password1";

            User u = new User();

            u.setUser(name, username, password, email);


            Assert.assertTrue("Ensure that `User` name is John!",
                    (u.getName().equals("John")));

            Assert.assertTrue("Ensure that `User` username is j123!",
                    (u.getUsername().equals("j123")));

            Assert.assertTrue("Ensure that `User` password is password1!",
                    (u.getPassword().equals("password1")));

            Assert.assertTrue("Ensure that `User` email is j@gmail.com!",
                    (u.getEmail().equals("j@gmail.com")));
        }

        @Test(timeout = 1000)
        public void setFriendsTest() {

            String name = "John";
            String username = "j123";
            String email = "j@gmail.com";
            String password = "password1";

            User u = new User();

            u.setUser(name, username, password, email);

            String name1 = "Jake";
            String username1 = "j456";
            String email1 = "jake@gmail.com";
            String password1 = "password2";

            User u1 = new User();

            u.setUser(name1, username1, password1, email1);

            String name2 = "Kate";
            String username2 = "Kt";
            String email2 = "k@gmail.com";
            String password2 = "password3";

            User u2 = new User();

            u.setUser(name2, username2, password2, email2);

            List<String> friends = new ArrayList<String>();
            friends.add("j456");
            friends.add("Kt");

            u.setFriends(friends);

            Assert.assertTrue("Ensure that `User` friend is j456",
                    (u.getFriends().get(0).equals("j456")));

            Assert.assertTrue("Ensure that `User` is friends with Kt",
                    (u.Friend("Kt")));
            

        }


    }


}
