package common.dataTypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

public class OrderPayment {
    public String id;
    public int customerId;
    public int brandId;
    public String saleOrderId;
    public int paymentType;
    public int transactionTypeId;
    public long payTime;
    public float totalAmount;

    public OrderPayment(String id, int customerId, int brandId, String saleOrderId, int paymentType, long payTime, float totalAmount){
        this.id=id;
        this.customerId=customerId;
        this.brandId=brandId;
        this.saleOrderId=saleOrderId;
        this.paymentType=paymentType;
        if(this.paymentType==1){
            Random rnd=new Random();
            this.transactionTypeId=rnd.nextInt(2000000000);
        }

        this.payTime=payTime;
        this.totalAmount=totalAmount;
    }
    @Override
    public String toString() {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}