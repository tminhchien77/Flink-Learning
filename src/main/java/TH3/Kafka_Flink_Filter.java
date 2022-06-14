package TH3;

import common.*;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

public class Kafka_Flink_Filter {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.1.12.183:9092");
        properties.setProperty("group.id", "group1");

        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer <>("Chien-topic-filter",  new SimpleStringSchema(), properties);
        DataStream<String> strInputStream = env.addSource(kafkaConsumer);

        DataStream<MessageModel> message = strInputStream.flatMap(new Tokenizer()).filter(new Filter());

        DataStream<String> filteredMessage = message.flatMap(new FilterMessageModel());
        filteredMessage.addSink(new FlinkKafkaProducer<>("10.1.12.183:9092",
                "Chien-topic-filter",
                new SimpleStringSchema()));
        env.execute();
    }
}
