package common;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID=1L;
    private String sender; //发送者
    private String getter; //接收者
    private String content; //内容
    private String sendTime; //发送时间
    private String messageType; //消息类型

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
