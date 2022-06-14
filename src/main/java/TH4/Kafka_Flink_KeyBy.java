package TH4;

import common.*;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

public class Kafka_Flink_KeyBy {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.1.12.183:9092");
        properties.setProperty("group.id", "group1");

        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer <>("Chien-topic",  new SimpleStringSchema(), properties);
        DataStream<String> strInputStream = env.addSource(kafkaConsumer);
        strInputStream.print();

        DataStream<MessageModel> message = strInputStream.flatMap(new Tokenizer());
        SingleOutputStreamOperator<Tuple2<Integer, Integer>> groupMessage=message.keyBy(new KeyByDescription()).process(new StateClass());
        DataStream<String> strGroupMessage = groupMessage.flatMap(new ConvertTupleToString());

        strGroupMessage.addSink(new FlinkKafkaProducer<>("10.1.12.183:9092",
                "Chien-topic",
                new SimpleStringSchema()));
        groupMessage.print();
        env.execute();

//        DataStream<String> filteredMessage = message.flatMap(new FilterMessageModel());
//        filteredMessage.addSink(new FlinkKafkaProducer<>("10.1.12.183:9092",
//                "Chien-topic-filter",
//                new SimpleStringSchema()));
    }
}
