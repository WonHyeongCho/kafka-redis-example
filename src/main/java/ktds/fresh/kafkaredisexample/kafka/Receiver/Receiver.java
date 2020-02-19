package ktds.fresh.kafkaredisexample.kafka.Receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ktds.fresh.kafkaredisexample.alarmService.AlarmService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    AlarmService alarmService;

    @KafkaListener(topics = "msa_test_20200219")
    public void receive(ConsumerRecord consumerRecord) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payload = objectMapper.readValue(consumerRecord.value().toString(), Map.class);

        logger.info("KafkaListener - Payload: " + payload.toString());

        String service = payload.get("service").toString();
        String key = payload.get("key").toString();

        // 습득물 서비스가 보냄
        if(service.equals("FindService")){
            // 물품을 습득했을 때
            if(key.equals("find")){
                String category = payload.get("category").toString();
                alarmService.getLostItemInfo(category);
            }
        }
    }
}
