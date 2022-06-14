package TH6.sources;

import common.dataTypes.CustomerPayment;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class SimpleCustomerPaymentSchema implements DeserializationSchema<CustomerPayment>, SerializationSchema<CustomerPayment> {

    @Override
    public CustomerPayment deserialize(byte[] bytes) throws IOException {
        return null;
    }

    @Override
    public boolean isEndOfStream(CustomerPayment customerPayment) {
        return false;
    }

    @Override
    public byte[] serialize(CustomerPayment customerPayment) {
        return new byte[0];
    }

    @Override
    public TypeInformation<CustomerPayment> getProducedType() {
        return null;
    }
}
