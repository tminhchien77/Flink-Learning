package common;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class ProcessWindow  extends ProcessWindowFunction<MessageModel, String, Integer, TimeWindow> {
    @Override
    public void process(Integer key,
                        ProcessWindowFunction<MessageModel, String, Integer, TimeWindow>.Context context,
                        Iterable<MessageModel> items,
                        Collector<String> out) throws Exception {
        int sum=0;
        for (MessageModel item: items) {
            sum+= item.Quantity;
        }
        String result = "[{\"Id\": \""+key+"\"," +
                "\"Quantity\": \""+sum+"\"" +
                "}]";
        out.collect(result);
    }
}
