package ktds.fresh.kafkaredisexample.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {
    private String topic;
    private String brokers;

    public MyConsumer(String topic, String brokers){
        this.topic = topic;
        this.brokers = brokers;
    }

    public StringBuilder consume(){
        /* 카프카 프로퍼티 설정 */
        Properties properties;
        properties = new Properties();
        properties.put("bootstrap.servers", this.brokers);
        properties.put("group.id", "ktds.fresh");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        /* Kafka Consumer 연결 */
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));

        ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(500);

        StringBuilder stringBuilder = new StringBuilder();

        for(ConsumerRecord<String, String> record : consumerRecords){
            stringBuilder.append(record.value());
            stringBuilder.append(",");
        }

        return stringBuilder;
    }
}
