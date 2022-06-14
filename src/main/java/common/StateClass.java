package common;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple0;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

public class StateClass extends KeyedProcessFunction<Integer, MessageModel, Tuple2<Integer, Integer>> {
    private transient ValueState<Integer> sum;
    @Override
    public void processElement(MessageModel item, KeyedProcessFunction<Integer, MessageModel, Tuple2<Integer, Integer>>.Context context, Collector<Tuple2<Integer, Integer>> out) throws Exception {
        int quantity= sum.value()==null? item.Quantity : sum.value()+ item.Quantity;
        sum.update(quantity);
        out.collect(new Tuple2<Integer, Integer>(item.Id, quantity));
    }
     public void open(Configuration parameters){
         ValueStateDescriptor<Integer> value=new ValueStateDescriptor<Integer>("sum", Integer.class);
         sum= getRuntimeContext().getState(value);
     }
}
