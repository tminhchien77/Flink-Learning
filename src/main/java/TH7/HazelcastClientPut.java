package TH7;

import TH6.sources.OrderPaymentKeyByDescription;
import TH6.sources.OrderPaymentTokenizer;
import TH7.resources.ProcessSinkOrderPayment;
import common.SimpleStringSchema;
import common.dataTypes.CustomerPayment;
import common.dataTypes.OrderPayment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.ProcessingTimeTrigger;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.*;

public class HazelcastClientPut {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.1.12.183:9092");
        properties.setProperty("group.id", "group1");

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer <>("OrderPayment_Chien188210",  new SimpleStringSchema(), properties);
        DataStream<String> strInputStream = env.addSource(consumer);
        DataStream<OrderPayment> message=strInputStream.flatMap(new OrderPaymentTokenizer());
        DataStream<CustomerPayment>
                groupMessage= message.keyBy(new OrderPaymentKeyByDescription())
                .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
                .trigger(ProcessingTimeTrigger.create())
                .process(new ProcessSinkOrderPayment("10.1.6.216:5701"));
        env.execute();
    }
}
