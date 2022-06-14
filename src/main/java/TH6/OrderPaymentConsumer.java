package TH6;

import TH6.sources.*;
import common.SimpleStringSchema;
import common.dataTypes.CustomerPayment;
import common.dataTypes.OrderPayment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.util.serialization.KeyedSerializationSchema;

import java.util.Properties;

public class OrderPaymentConsumer {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.1.12.183:9092");
        properties.setProperty("group.id", "group1");

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer <>("OrderPayment_Chien188210",  new SimpleStringSchema(), properties);
        DataStream<String> strInputStream = env.addSource(consumer);
        DataStream<OrderPayment> message=strInputStream.flatMap(new OrderPaymentTokenizer());
        DataStream<CustomerPayment>
        groupMessage=message.keyBy(new OrderPaymentKeyByDescription())
                            .window(TumblingProcessingTimeWindows.of(org.apache.flink.streaming.api.windowing.time.Time.seconds(10)))
                            .process(new OrderPaymentProcess());
        DataStream<String> strgroupMessage=groupMessage.flatMap(new CustomerPaymentToString());
        strgroupMessage.addSink(new FlinkKafkaProducer<String>("10.1.12.183:9092",
                "CustomerPayment_Chien188210",
                new SimpleStringSchema()));

        /*groupMessage.addSink(new FlinkKafkaProducer<CustomerPayment>("10.1.12.183:9092",
                "CustomerPayment_Chien188210",
                new SimpleStringSchema()));*/
        env.execute();
    }

}
