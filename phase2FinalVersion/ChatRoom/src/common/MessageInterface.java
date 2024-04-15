package common;

import java.io.Serializable;
/**
 * The Message class represents a message exchanged between users.
 * It implements the Serializable interface for object serialization.
 *
 */
public interface MessageInterface extends Serializable {
    String getSender();

    void setSender(String sender);

    String getGetter();

    void setGetter(String getter);

    String getContent();

    void setContent(String content);

    String getSendTime();

    void setSendTime(String sendTime);

    String getMessageType();

    void setMessageType(String messageType);
}