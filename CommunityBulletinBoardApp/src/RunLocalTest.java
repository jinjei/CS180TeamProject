import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.lang.reflect.Modifier;

/**
 * A framework to run public test cases for PJ3.
 *
 * <p>Purdue University -- CS18000 -- Spring 2021</p>
 *
 * @author J Morris Purdue CS
 * @version Feb 20, 2024
 */

@RunWith(Enclosed.class)
public class RunLocalTest {

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
                    1, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void UserConstructorTest() {

            String name = "John";
            String username = "j123";
            String email = "j@gmail.com";
            String password = "password1";
            String bio = "hi";

            User u = new User(name,username,email,password);
            u.updateBio(bio);


            Assert.assertTrue("Ensure that `User` Bio is hi!",
                    (u.getBio().equals("hi")));

            Assert.assertTrue("Ensure that `User` name is John!",
                    (u.getName().equals("John")));

            Assert.assertTrue("Ensure that `User` username is j123!",
                    (u.getUsername().equals("j123")));

            Assert.assertTrue("Ensure that `User` email is j@gmail.com!",
                    (u.getEmail().equals("j@gmail.com")));

            Assert.assertTrue("Ensure that `User` password is password1!",
                    (u.getPassword().equals("password1")));

        }

    }

}