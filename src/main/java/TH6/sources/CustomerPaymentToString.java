package TH6.sources;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.dataTypes.CustomerPayment;
import common.dataTypes.OrderPayment;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.List;

public class CustomerPaymentToString implements FlatMapFunction<CustomerPayment, String> {
    @Override
    public void flatMap(CustomerPayment customerPayment, Collector<String> collector) throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        List<String> strList= Arrays.asList("["+objectMapper.writeValueAsString(customerPayment)+"]");
        for (String item:strList) {
            collector.collect(item);
        }
    }
}
