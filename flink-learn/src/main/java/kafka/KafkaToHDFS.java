package kafka;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.fs.StringWriter;
import org.apache.flink.streaming.connectors.fs.bucketing.Bucketer;
import org.apache.flink.streaming.connectors.fs.bucketing.BucketingSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class KafkaToHDFS {

    public static void main(String[] args) throws Exception {

        // set up the streaming execution environment
        final StreamExecutionEnvironment env =StreamExecutionEnvironment.getExecutionEnvironment();

        env.enableCheckpointing(5000);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "i4server:9092");
        props.setProperty("group.id", "i4-group");
        //earliest、latest
        props.put("auto.offset.reset", "earliest");
        //props.setProperty("fs.default-scheme","hdfs://i4ns");

        FlinkKafkaConsumer<String> flinkKafkaConsumer = new FlinkKafkaConsumer<String>("i4-flink-topic", new SimpleStringSchema(), props);
        DataStream<String> keyedStream = env.addSource(flinkKafkaConsumer);
        keyedStream.print();

        BucketingSink<String> bucketingSink = new BucketingSink<>("/home/0/flink/1");
        BucketingSink<String> bucketingSink1 = bucketingSink.setBucketer((Bucketer<String>) (clock, basePath, value) -> {
            return basePath;
        });
        bucketingSink.setWriter(new StringWriter<>())
                .setBatchSize(1024 * 1024 )
                .setBatchRolloverInterval(2000);

        keyedStream.addSink(bucketingSink);

        env.execute("Flink-KafkaToHDFS");
    }
}
