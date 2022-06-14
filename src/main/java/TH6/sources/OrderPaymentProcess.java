package TH6.sources;

import common.dataTypes.CustomerPayment;
import common.dataTypes.OrderPayment;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/*public class OrderPaymentProcess extends KeyedProcessFunction<Tuple2<Integer, Integer>, OrderPayment, CustomerPayment> {
    private transient ValueState<Tuple2<Float, Integer>> valueState;

    @Override
    public void processElement(OrderPayment orderPayment, KeyedProcessFunction<Tuple2<Integer, Integer>, OrderPayment, CustomerPayment>.Context context, Collector<CustomerPayment> collector) throws Exception {
        float amount=valueState.value()==null? orderPayment.totalAmount: orderPayment.totalAmount+valueState.value()._1;
        int numPay=valueState.value()==null?1:valueState.value()._2+1;
        valueState.update(new Tuple2<Float, Integer>(amount, numPay));
        collector.collect(new CustomerPayment(orderPayment.customerId, orderPayment.brandId, orderPayment.payableType, orderPayment.transactionTypeId, numPay, amount));
    }

}*/
public class OrderPaymentProcess extends ProcessWindowFunction<OrderPayment, CustomerPayment, Tuple2<Integer, Integer>, TimeWindow> {
    @Override
    public void process(Tuple2<Integer, Integer> key,
                        ProcessWindowFunction<OrderPayment, CustomerPayment, Tuple2<Integer, Integer>, TimeWindow>.Context context,
                        Iterable<OrderPayment> elements,
                        Collector<CustomerPayment> collector) throws Exception {
        float totalAmount=0;
        int numPay=0;
        for (OrderPayment item: elements) {
            totalAmount+=item.totalAmount;
            numPay++;
        }
        collector.collect(new CustomerPayment(key.f0, elements.iterator().next().brandId, key.f1, elements.iterator().next().transactionTypeId, numPay, totalAmount));

    }
}
