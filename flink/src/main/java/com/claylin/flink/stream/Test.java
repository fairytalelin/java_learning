package com.claylin.flink.stream;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.StreamQueryConfig;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.api.java.Tumble;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author linwucai
 * 2019-09-21 09:40
 **/

public class Test {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<St> source = env.socketTextStream("localhost", 10028)
                .map(new MapFunction<String, St>() {
                    @Override
                    public St map(String value) throws Exception {
                        String[] strs = value.split(",");
                        if (strs.length < 2) {
                            return null;
                        }
                        St st = new St();
                        st.uid = strs[0];
                        st.name = strs[1];
                        return st;
                    }
                });

        //env.setStateBackend()

        StreamTableEnvironment tabEnv = StreamTableEnvironment.create(env);
        StreamQueryConfig queryConfig = tabEnv.queryConfig().withIdleStateRetentionTime(Time.hours(24), Time.hours(25));

        Table student = tabEnv.fromDataStream(source, "uid, name");

        Table table = student.groupBy("uid, name").select("uid, name").distinct();

        DataStream<Tuple2<Boolean, St>> uniqStreamTuple2 = tabEnv.toRetractStream(table, St.class, queryConfig);

        DataStream<St> uniqStream = uniqStreamTuple2.map((tuple -> tuple.f1));

        Table uniqTab = tabEnv.fromDataStream(uniqStream, "uid, name, proctime.proctime");

        Table result = uniqTab.window(Tumble.over("10.seconds").on("proctime").as("w")).groupBy("name, w")
                .select("name, name.count as cnt, w.start as wStart, w.end as wEnd, w.proctime as wProctime");

        DataStream<Result> restulStream = tabEnv.toAppendStream(result, Result.class, queryConfig);

        restulStream.print();

        env.setParallelism(1);

        env.execute(Test.class.getSimpleName());
    }

    public static class St {
        public String uid;
        public String name;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("St{");
            sb.append("uid='").append(uid).append('\'');
            sb.append(", name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static class Result {
        public String name;
        public long cnt;
        public Timestamp wStart;
        public Timestamp wEnd;
        public Timestamp wProctime;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Result{");
            sb.append("uid='").append(name).append('\'');
            sb.append(", cnt=").append(cnt);
            sb.append(", wStart=").append(wStart);
            sb.append(", wEnd=").append(wEnd);
            sb.append(", wProctime=").append(wProctime);
            sb.append('}');
            return sb.toString();
        }
    }
}
