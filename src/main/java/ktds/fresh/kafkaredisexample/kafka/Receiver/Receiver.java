package ktds.fresh.kafkaredisexample.kafka.Receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = "msa_test_20200219")
    public void receive(ConsumerRecord consumerRecord) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payload = objectMapper.readValue(consumerRecord.value().toString(), Map.class);

        logger.info("KafkaListener - Payload: " + payload.toString());
    }
}
