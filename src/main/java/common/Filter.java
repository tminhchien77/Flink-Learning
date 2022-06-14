package common;

public class Filter implements org.apache.flink.api.common.functions.FilterFunction<MessageModel> {
    @Override
    public boolean filter(MessageModel messageModel) throws Exception {
        return messageModel.Quantity>10;
    }
}