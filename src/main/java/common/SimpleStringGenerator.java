package common;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class SimpleStringGenerator implements SourceFunction<String> {
    private static final long serialVersionUID = 2174904787118597072L;
    boolean running = true;
    long i = 0;
    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        while(running) {
            MessageModel message = new MessageModel();
            ctx.collect(message.toString());
            Thread.sleep(500);
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
