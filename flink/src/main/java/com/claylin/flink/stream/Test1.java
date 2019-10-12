package com.claylin.flink.stream;
import com.claylin.flink.stream.CallbackItem;
import com.claylin.flink.stream.PushIdCallback;
import com.claylin.flink.stream.Test;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.StreamQueryConfig;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;
import org.apache.flink.util.TernaryBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by lwc on 2019/8/22.
 * 统计fcm通道反馈push的发送状态:ack、nack
 */
public class Test1 {

    private static final String checkpointPath = "hdfs:///data/hadoop/dfs/data/flink/flink-checkpoints";

    public static void main(String[] args) throws Exception {
        String topic = "SaveLog";

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);


        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
        StreamQueryConfig qConfig = tableEnv.queryConfig();
        qConfig.withIdleStateRetentionTime(Time.seconds(20), Time.minutes(6));

        DataStream<CallbackItem> callbackItemStream = env.socketTextStream("localhost", 10028)
                .map(new MapFunction<String, CallbackItem>() {
                    @Override
                    public CallbackItem map(String value) throws Exception {
                        String[] strs = value.split(",");
                        if (strs.length < 2) {
                            return null;
                        }
                        CallbackItem st = new CallbackItem();
                        st.pushId = strs[0];
                        st.hdid = strs[1];

                        return st;
                    }
                });


        Table itemsTable = tableEnv.fromDataStream(callbackItemStream, "pushId, hdid");

        tableEnv.registerTable("items_table", itemsTable);

        String groupSql = "select pushId, hdid from " + itemsTable + " group by pushId, hdid";

        Table groupTab = tableEnv.sqlQuery(groupSql);

        DataStream<Tuple2<Boolean, CallbackItem>> groupRetractStream = tableEnv.toRetractStream(groupTab, CallbackItem.class, qConfig);

        DataStream<CallbackItem> groupStream = groupRetractStream.map((tuple2) -> tuple2.f1);

        Table outputTable = tableEnv.fromDataStream(groupStream, "pushId, hdid, proctime.proctime");
        tableEnv.registerTable("output_table", outputTable);
        String windowGroupSql = "select pushId, count(1) as cnt, TUMBLE_START(proctime, INTERVAL '10' SECOND) as wStart, TUMBLE_END(proctime, INTERVAL '10' SECOND) as wEnd from " + outputTable + " group by pushId, TUMBLE(proctime, INTERVAL '10' SECOND)";

        Table windowGroup = tableEnv.sqlQuery(windowGroupSql);

        DataStream<Row> resultStream = tableEnv.toAppendStream(windowGroup, PushIdCallback.class, qConfig).map(new MapFunction<PushIdCallback, Row>() {
            @Override
            public Row map(PushIdCallback value) throws Exception {
                Row row = new Row(4);
                row.setField(0, value.pushId);
                row.setField(1, value.cnt);
                row.setField(2, value.wStart.getTime() + 8 * 60 * 60 * 1000);
                row.setField(3, value.wEnd.getTime() + 8 * 60 * 60 * 1000);
                return row;
            }
        });

        resultStream.print();

        env.execute(Test1.class.getSimpleName());
    }
}