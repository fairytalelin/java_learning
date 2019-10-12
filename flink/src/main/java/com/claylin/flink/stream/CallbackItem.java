package com.claylin.flink.stream;

import java.util.StringJoiner;

/**
 * @author linwucai
 * 2019-09-21 15:20
 **/

public class CallbackItem {
    public String pushId;
    public String hdid;

    @Override
    public String toString() {
        return new StringJoiner(", ", CallbackItem.class.getSimpleName() + "[", "]")
                .add("pushId='" + pushId + "'")
                .add("hdid='" + hdid + "'")
                .toString();
    }
}
