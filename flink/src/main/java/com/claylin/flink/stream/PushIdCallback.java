package com.claylin.flink.stream;

import java.sql.Timestamp;
import java.util.StringJoiner;

/**
 * @author linwucai
 * 2019-09-21 15:24
 **/

public class PushIdCallback {
    public String pushId;
    public long cnt;
    public Timestamp wStart;
    public Timestamp wEnd;

    @Override
    public String toString() {
        return new StringJoiner(", ", PushIdCallback.class.getSimpleName() + "[", "]")
                .add("pushId='" + pushId + "'")
                .add("cnt=" + cnt)
                .add("wStart=" + wStart)
                .add("wEnd=" + wEnd)
                .toString();
    }
}
