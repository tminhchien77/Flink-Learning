package common;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class ConvertTupleToString implements FlatMapFunction<Tuple2<Integer, Integer>, String> {
    @Override
    public void flatMap(Tuple2<Integer, Integer> t, Collector<String> out) {
        String kq = "[{\"Id\": \""+t.f0+"\"," +
                "\"Quantity\": \""+t.f1+"\"" +
                "}]";
        out.collect(kq);

    }
}
