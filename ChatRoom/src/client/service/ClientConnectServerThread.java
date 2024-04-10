package client.service;

import UI.UserInterfaceFrame;
import common.Message;
import common.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    //该线程需要持有Socket对象
    private Socket socket;
    private String userId;
    private ObjectInputStream ois;
    private UserInterfaceFrame frame;

    public ClientConnectServerThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
        this.frame = UserInterfaceFrame.getInstance();
    }

    @Override
    public void run() {
        //后台Socket服务器要一直保持通讯
        while (true) {
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                //如果在流中没有读取到这一对象，则会停顿在此处
                Message message = (Message) ois.readObject();
                actionByMessageType(message);//根据收到的消息类型做出反应
            } catch (Exception e) {
                /*e.printStackTrace();*/
            }
        }
    }

    public void actionByMessageType(Message message) {
        switch (message.getMessageType()) {
            case MessageType.MESSAGE_RET_ONLINE_FRIEND:
                //客户收到服务器的返回信息，信息内容就是在线人数
                String[] split = message.getContent().split(" ");
                System.out.println("在线用户如下：");
                for (String name : split) {
                    System.out.println("用户：" + name);
                }
                break;
            case MessageType.MESSAGE_COMM_MES:
                //收到普通消息，提出内容、发送者、发送时间打印在控制台
                System.out.println(message.getSendTime());
                if (message.getGetter().equals("All")) {

                    StringBuffer strBuf = frame.getStrAllBuf();
                    strBuf.append(message.getContent());
                    System.out.println(strBuf.toString());
                    frame.setAllMsg(strBuf.toString());
                    //说明这是群发消息
                    System.out.println("【" + message.getSender() + "】对【所有人】说：");
                } else {
                    StringBuffer strBuf = frame.getStrBuf();
                    strBuf.append(message.getContent());
                    System.out.println(strBuf.toString());
                    frame.setMsg(strBuf.toString());

                    System.out.println("【" + message.getSender() + "】对【我】说：");

                }
                System.out.print(message.getContent() + "\n\n请输入你的选择：");
                break;
            case MessageType.MESSAGE_CLIENT_NO_EXIST:
                //私聊目标不存在
                System.out.println("客户【" + message.getGetter() + "】不存在，无法发送！");
                break;
            case MessageType.MESSAGE_CLIENT_OFFLINE:
                System.out.println("客户【" + message.getGetter() + "】不在线，其在线后会收到消息！");
        }

    }

    public Socket getSocket() {
        return socket;
    }


}
