package common;

import org.apache.flink.api.java.functions.KeySelector;

public class KeyByDescription implements KeySelector<MessageModel, Integer> {
    @Override
    public Integer getKey(MessageModel messageModel) throws Exception {
        return messageModel.Id;
    }
}
