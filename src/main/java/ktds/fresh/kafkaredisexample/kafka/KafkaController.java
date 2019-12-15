package ktds.fresh.kafkaredisexample.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Value("${spring.kafka.bootstrap-servers}")
    private String brokers;

    @RequestMapping("")
    public String hello(){
        logger.info("URL: /kafka");
        return "Hello";
    }

    @RequestMapping("/send")
    public void send(@RequestBody Map<String, Object> param){
        String topic = (String)param.get("topic");
        String text = (String)param.get("text");

        logger.info("URL: /kafka/send, PARAM: " + param.toString());

        MyProducer producer = new MyProducer(topic, brokers);
        producer.produce(text);
    }

    @RequestMapping("/received")
    public String[] received(@RequestBody Map<String, Object> param){
        String topic = (String)param.get("topic");

        MyConsumer consumer = new MyConsumer(topic, brokers);
        StringBuilder stringBuilder = consumer.consume();

        return stringBuilder.toString().split(",");
    }


}
