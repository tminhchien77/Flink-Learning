package common;

public class MessageModel {
    public MessageModel(){
        DataGenerator d = new DataGenerator();
        this.Id = d.Id();
        this.Quantity=d.Quantity();

    }
    public MessageModel(int i, int q){
        this.Id=i;
        this.Quantity=q;
    }
    public int Id;
    public int Quantity;

    @Override
    public String toString() {

        return "[{\"Id\": \""+Id+"\"," +
                "\"Quantity\": \""+Quantity+"\"" +
                "}]";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof MessageModel &&
                this.Id == ((MessageModel) other).Id;
    }
/*
    public static void main(String[] args) throws Exception {
        // set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        */
/*
         * Here, you can start creating your execution plan for Flink.
         *
         * Start with getting some data from the environment, like
         * 	env.readTextFile(textPath);
         *
         * then, transform the resulting DataStream<String> using operations
         * like
         * 	.filter()
         * 	.flatMap()
         * 	.join()
         * 	.coGroup()
         *
         * and many more.
         * Have a look at the programming guide for the Java API:
         *
         * https://flink.apache.org/docs/latest/apis/streaming/index.html
         *
         *//*

        // add a simple source which is writing some strings
        DataStream<String> messageStream = env.addSource(new ex2.DataGenerator.SimpleStringGenerator());

        // write stream to Kafka
        messageStream.addSink(new FlinkKafkaProducer<>("10.1.12.183:9092",
                "test_topic5",
                new ex2.DataGenerator.SimpleStringSchema()));


        // execute program
        env.execute("Flink producer");
    }
*/
}
