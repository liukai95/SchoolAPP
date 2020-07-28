package com.flower.youth.dao;

import java.util.List;

/**
 * Created by CJX on 2017/8/3.
 */

public class LoadFileReturn {

    int Code;

    int MessageNum;

    List<String> Message;

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

    public List<String> getMessage() {
        return Message;
    }

    public void setMessage(List<String> message) {
        Message = message;
    }
}
