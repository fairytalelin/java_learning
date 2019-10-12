package com.claylin.flink.stream;

import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;
import org.apache.flink.util.Collector;

import java.util.Collections;

public class WindowWordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
        DataStream<Item> stream = env.socketTextStream("127.0.0.1", 10022)
                .map(new MapFunction<String, Item>() {
                    @Override
                    public Item map(String value) throws Exception {
                        String[] fields = value.split("\\s");
                        if (fields.length < 3) {
                            return null;
                        }
                        Item item = new Item();
                        item.pushId = fields[0];
                        item.hdid = fields[1];
                        item.msgType = fields[2];
                        return item;
                    }
                })
                .keyBy("pushId", "hdid", "msgType")
                .fold(new Item(), new FoldFunction<Item, Item>() {
                    @Override
                    public Item fold(Item accumulator, Item value) throws Exception {
                        if (accumulator.msgType == null)
                            accumulator.msgType = value.msgType;
                        if (accumulator.hdid == null)
                            accumulator.hdid = value.hdid;
                        if (accumulator.pushId == null)
                            accumulator.pushId = value.pushId;
                        return accumulator;
                    }
                });

        Table pushStatistics = tableEnv.fromDataStream(stream, "pushId," +
                "hdid, msgType, proctime.proctime");
        tableEnv.registerTable("push_statistics", pushStatistics);

        pushStatistics.distinct();

        String windowGroupSql = "select pushId, msgType, count(1) as cnt, TUMBLE_START(proctime, INTERVAL '10' SECOND) as wStart, TUMBLE_END(proctime, INTERVAL '10' SECOND) as wEnd from " + pushStatistics + " group by pushId, msgType, TUMBLE(proctime, INTERVAL '10' SECOND)";

        Table windowGroup = tableEnv.sqlQuery(windowGroupSql);

        DataStream<Row> ph = tableEnv.toAppendStream(windowGroup, Row.class);

        ph.print();

        //dataStream = env.fromCollection(Collections.singleton("2312")).flatMap(new Splitter());


        //dataStream.print();
        env.execute("Window WordCount");
    }

    public static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            for (String word : value.split(" ")) {
                out.collect(new Tuple2<>(word, 1));
            }
        }
    }

    public static class Item {
        public String pushId;
        public String hdid;
        public String msgType;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Item{");
            sb.append("pushId='").append(pushId).append('\'');
            sb.append(", hdid='").append(hdid).append('\'');
            sb.append(", msgType='").append(msgType).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
