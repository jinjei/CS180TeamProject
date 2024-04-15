package common;

import java.io.Serializable;

/**
 * Team Project(Project 05) -- Message
 * <p>
 * Represents a message object for client-server communication.
 * This class is designed to encapsulate all necessary information for messages transmitted
 * between clients and servers, supporting a variety of communication types within a networked application.
 * It is serializable, allowing it to be transmitted over network streams.
 *
 * @author Lab01, Team 4
 * @version Apr 10, 2024
 */
public class Message implements Serializable, MessageInterface {
    private static final long serialVersionUID = 1L; //Version compatibility mark
    private String sender;
    private String getter; //receiver
    private String content;
    private String sendTime;
    private String messageType;

    public Message(String sender, String messageType) {
        this.sender = sender;
        this.messageType = messageType;
    }

    public Message() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}