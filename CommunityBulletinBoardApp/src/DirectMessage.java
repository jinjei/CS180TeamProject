import java.io.*;
import java.util.ArrayList;

/**
 * Team Project(Project 05) -- DirectMessage
 * <p>
 * Interface for a class that implements direct messaging
 *
 * @author Lab01, Team 4
 * @version Apr 1, 2024
 */
public interface DirectMessage {

    public boolean sendMssg(User sender, User receiver, String message);

    public boolean deleteMssg(User sender, User deleter, String message);

    public boolean receiveMssg(User sender, User receiver, String message);

    public boolean blockMssg(User receiver, String message);

    public boolean blockUser(User sender, User block);

    public boolean restrictMssg(User sender, User receiver);


}
