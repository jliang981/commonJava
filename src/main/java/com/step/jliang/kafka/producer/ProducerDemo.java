package com.step.jliang.kafka.producer;

/**
 * Created by jliang on 17/5/20.
 */

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        //zk
        props.put("zk.connect", "localhost:2181");
        //kafka broker
        props.put("bootstrap.servers", "localhost:9092");

        props.put("batch.size", 16384);

        //serialize
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.put("serializer.class", "kafka.serializer.StringEncoder");
        // ProducerConfig config = new ProducerConfig(props);
        // Producer<String, String> producer = new Producer<String, String>(config);

        Producer<String, String> producer = new KafkaProducer<>(props);

        // read socket
        for (int i = 1; i <= 10; i++) {
            //Thread.sleep(50);
            producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i * 2)));
        }
        producer.close();
    }
}
