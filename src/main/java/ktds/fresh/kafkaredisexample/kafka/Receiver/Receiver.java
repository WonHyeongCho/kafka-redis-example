package ktds.fresh.kafkaredisexample.kafka.Receiver;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = "msa_test_20200219")
    public void receive(ConsumerRecord consumerRecord, Acknowledgment acknowledgment){
        logger.info("KafkaListener - ConsumerRecord: " + consumerRecord.toString() + ", " +
                "Acknowledgment: " + acknowledgment.toString());
    }
}
