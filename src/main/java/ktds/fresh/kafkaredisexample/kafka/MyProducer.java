package ktds.fresh.kafkaredisexample.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer {
    private String topic;
    private String brokers;

    public MyProducer(String topic, String brokers){
        this.topic = topic;
        this.brokers = brokers;
    }

    public void produce(String text){
        /* 카프카 프로퍼티 설정 */
        Properties properties;
        properties = new Properties();
        properties.put("bootstrap.servers", this.brokers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        /* Kafka Producer 연결 */
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        /* Kafka로 데이터 전송 */
        producer.send(new ProducerRecord<String, String>(topic, text));

        /* Kafka Producer 종료 */
        producer.flush();
        producer.close();
    }
}
