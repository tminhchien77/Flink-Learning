package TH6.sources;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.dataTypes.OrderPayment;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.List;

public class OrderPaymentTokenizer implements FlatMapFunction<String, OrderPayment> {
    @Override
    public void flatMap(String value, Collector<OrderPayment> collector) throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        List<OrderPayment> orderPaymentList= Arrays.asList(objectMapper.readValue(value, OrderPayment[].class));
        for (OrderPayment item:orderPaymentList) {
            collector.collect(item);
        }
    }
}
