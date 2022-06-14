package common.dataTypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

public class OrderPayment {
    public String id;
    public int customerId;
    public int brandId;
    public String saleOrderId;
    public int payableType;
    public int transactionTypeId;
    public long payTime;
    public float totalAmount;
    public OrderPayment(){

    };
    public OrderPayment(String id, int customerId, int brandId, String saleOrderId, int payableType, int transactionTypeId, long payTime, float totalAmount){
        this.id=id;
        this.customerId=customerId;
        this.brandId=brandId;
        this.saleOrderId=saleOrderId;
        this.payableType=payableType;
        this.transactionTypeId=transactionTypeId;
        this.payTime=payTime;
        this.totalAmount=totalAmount;
    }
    public OrderPayment(String id, int customerId, int brandId, String saleOrderId, int payableType, long payTime, float totalAmount){
        this.id=id;
        this.customerId=customerId;
        this.brandId=brandId;
        this.saleOrderId=saleOrderId;
        this.payableType=payableType;
        if(this.payableType==1){
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
            String result="["+objectMapper.writeValueAsString(this)+"]";
            return result;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}