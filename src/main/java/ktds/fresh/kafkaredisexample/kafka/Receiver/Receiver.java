package ktds.fresh.kafkaredisexample.kafka.Receiver;

import ktds.fresh.kafkaredisexample.kafka.KafkaController;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

public class Receiver extends KafkaProperties.Listener {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(id = "ktds.fresh", topics = "TestTopic", containerFactory = "kafkaListenContainerFactory")
    public void receive(ConsumerRecord consumerRecord, Acknowledgment acknowledgment){
        logger.info("ConsumerRecord: " + consumerRecord.toString() + ", Acknowledgment: " + acknowledgment.toString());

    }
}
