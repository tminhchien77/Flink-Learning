package TH6.sources;

import common.dataTypes.OrderPayment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.time.Instant;
import java.util.Random;

public class OrderPaymentGenerator implements SourceFunction<String> {
    boolean running=true;
    @Override
    public void run(SourceContext<String> sourceContext) throws Exception {
        while(running) {
            OrderPayment orderPayment=new OrderPayment(randomString() ,randomNumber(), randomNumber(), randomString(), randomPaymentType(), Instant.now().toEpochMilli(), randomAmount());
            sourceContext.collect(orderPayment.toString());
            Thread.sleep(500);
        }
    }

    @Override
    public void cancel() {

    }

    private String randomString(){
        Random rnd=new Random();
        Integer result=rnd.nextInt(2000000000);
        return result.toString();
    }
    private int randomNumber(){
        Random rnd=new Random();
        return rnd.nextInt(2000000000);
    }
    private int randomPaymentType(){
        Random rnd=new Random();
        return rnd.nextInt(4-1)+1;
    }
    private float randomAmount(){
        Random rnd=new Random();
        return rnd.nextInt(2000000000)+ rnd.nextFloat();
    }
}
