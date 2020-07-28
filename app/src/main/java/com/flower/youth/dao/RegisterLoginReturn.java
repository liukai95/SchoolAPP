package com.flower.youth.dao;

import java.util.List;

/**
 * Created by CJX on 2017/8/2.
 */

public class RegisterLoginReturn {

    int Code;

    int MessageNum;

    List<Account> Message;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public int getMessageNum() {
        return MessageNum;
    }

    public void setMessageNum(int messageNum) {
        MessageNum = messageNum;
    }

    public List<Account> getMessage() {
        return Message;
    }

    public void setMessage(List<Account> message) {
        Message = message;
    }
}
