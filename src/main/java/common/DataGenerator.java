package common;

import java.util.Random;

public class DataGenerator {
    /*public static class SimpleStringGenerator implements SourceFunction<String> {
        private static final long serialVersionUID = 2174904787118597072L;
        boolean running = true;
        long i = 0;
        @Override
        public void run(SourceContext<String> ctx) throws Exception {
            while(running) {
                ctx.collect("element-"+ (i++));
                Thread.sleep(500);
            }
        }

        @Override
        public void cancel() {
            running = false;
        }
    }


    public static class SimpleStringSchema implements DeserializationSchema<String>, SerializationSchema<String> {
        private static final long serialVersionUID = 1L;

        public SimpleStringSchema() {
        }

        public String deserialize(byte[] message) {
            return new String(message);
        }

        public boolean isEndOfStream(String nextElement) {
            return false;
        }

        public byte[] serialize(String element) {
            return element.getBytes();
        }

        public TypeInformation<String> getProducedType() {
            return TypeExtractor.getForClass(String.class);
        }
    }*/
    private static final int NUMBER_OF_ID = 10;
    private  static  final int MAX_OF_QUANTTITY=100;

    public DataGenerator(){

    }

    public int Id() {
        Random rnd = new Random();
        return rnd.nextInt( NUMBER_OF_ID-1 )+1;
    }

    public int Quantity() {
        Random rnd = new Random();
        return rnd.nextInt(MAX_OF_QUANTTITY-1)+1;
    }

}
