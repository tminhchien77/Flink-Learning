package TH6;

import TH6.sources.OrderPaymentGenerator;
import common.SimpleStringGenerator;
import common.SimpleStringSchema;
import common.dataTypes.OrderPayment;
import org.apache.flink.client.program.StreamContextEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

public class OrderPaymentProducer {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env=StreamContextEnvironment.getExecutionEnvironment();
        DataStream<String> messageStream = env.addSource(new OrderPaymentGenerator());
        messageStream.print();
        messageStream.addSink(new FlinkKafkaProducer<String>("10.1.12.183:9092",
                "OrderPayment_Chien188210",
                new SimpleStringSchema()));
        env.execute("Flink producer");
    }
}

