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
/**
 * Team Project(Project 05) -- UserTest
 * <p>
 * Test for Message class
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
@RunWith(Enclosed.class)
public class MessageTest {

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
        public void MessageClassTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = User.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `Message` is `public`!",
                    Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `Message` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `Message` extends `Object`!",
                    Object.class, superclass);
        }

        @Test(timeout = 1000)
        public void messgTest() {

            String sender = "Kt";
            String messageType = "string";

            Message newM = new Message(sender,messageType);

            Assert.assertTrue("Ensure that sender is Kt!",
                    (newM.getSender().equals("Kt")));

            Assert.assertTrue("Ensure that sender is Kt!",
                    (newM.getMessageType().equals("string")));

            Message mssgTwo = new Message();

            mssgTwo.setContent("hello");
            mssgTwo.setMessageType("string");
            mssgTwo.setSender("Jake");
            mssgTwo.setGetter("Kate");

            Assert.assertTrue("Ensure that content is hello",
                    (mssgTwo.getContent().equals("hello")));

            Assert.assertTrue("Ensure that messageType is string",
                    (mssgTwo.getMessageType().equals("string")));

            Assert.assertTrue("Ensure that sender is Jake",
                    (mssgTwo.getSender().equals("Jake")));

            Assert.assertTrue("Ensure that receiver is Kate",
                    (mssgTwo.getGetter().equals("Kate")));

            //send time setter & getter not required as this was purely
            //for testing purposes and not part of the project functionality.
        }



    }


}