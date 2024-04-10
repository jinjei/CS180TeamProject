package client;

import client.service.UserClientService;
import common.Utility;

public class QQView {
    private boolean loop = true; //控制是否显示菜单
    private String key = ""; //接收用户键盘输入
    private UserClientService userClientService = new UserClientService();

    public static void main(String[] args) {
        new QQView().mainMenu();
    }

    public void mainMenu() {
        while (loop) {
            System.out.println("============欢迎登录网络通信系统============");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            key = Utility.readString();
            switch (key) {
                case "1":
                    System.out.print("请输入用户号：");
                    String userId = Utility.readString();
                    System.out.print("请输入密 码：");
                    String pwd = Utility.readString();
                    //去服务端看看用户是否合法
                    if (userClientService.checkUser(userId, pwd)) {
                        System.out.println("============欢迎【" + userId + "】登录网络通信系统============");
                        //由此进入二级菜单
                        while (loop) {
                            System.out.println("============【" + userId + "】网络通信系统二级菜单============");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString();
                            String name; //发送给谁
                            String contents; //消息内容
                            switch (key) {
                                case "1":
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.print("群发内容：" );
                                    contents = Utility.readString();
                                    userClientService.sendAll(contents);
                                    break;
                                case "3":
                                    System.out.print("发送给：");
                                    name = Utility.readString();
                                    System.out.print("内容：" );
                                    contents = Utility.readString();
                                    userClientService.Send(name,contents);
                                    break;
                                case "9":
                                    userClientService.closedComm();
                                    System.out.println("客户端退出...");
                                    loop = false;
                                    break;
                            }
                            try {
                                Thread.sleep(5); //为了输出好看些
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("登录失败！");
                        break;
                    }
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}
