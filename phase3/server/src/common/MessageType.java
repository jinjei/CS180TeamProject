package common;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCESS = "1"; //login success
    String MESSAGE_LOGIN_FAIL = "2"; //login failure
    String MESSAGE_COMM_MES = "3";//common message
    String MESSAGE_GET_ONLINE_FRIEND = "4"; //get online user list
    String MESSAGE_RET_ONLINE_FRIEND = "5";//return online user list
    String MESSAGE_CLIENT_EXIT = "6"; //client request exit
    String MESSAGE_CLIENT_NO_EXIST = "7"; //receiver of the message does not exist
    String MESSAGE_CLIENT_OFFLINE = "8"; //receiver of the message is offline
    String MESSAGE_CLIENT_WITHDRAW="9";//withdraw
    String MESSAGE_CLIENT_WITHDRAWME="10";//withdraw

}