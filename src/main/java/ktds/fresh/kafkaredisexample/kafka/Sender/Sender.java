package ktds.fresh.kafkaredisexample.kafka.Sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

public class Sender {
    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Map<String, Object> payload) throws JsonProcessingException {
        logger.info("sending payload='{}'", payload.toString());

        // Map -> Json 으로 변환
        ObjectMapper objectMapper = new ObjectMapper();

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, objectMapper.writeValueAsString(payload));
        kafkaTemplate.send(producerRecord);
    }
}
