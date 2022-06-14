package common;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

public class FilterMessageModel implements FlatMapFunction<MessageModel, String> {
    @Override
    public void flatMap(MessageModel value, Collector<String> out) throws InterruptedException {
        System.out.println(value.toString());
        out.collect(value.toString());
    }
}
