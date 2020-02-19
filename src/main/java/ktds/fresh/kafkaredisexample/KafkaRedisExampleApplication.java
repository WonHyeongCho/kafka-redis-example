package ktds.fresh.kafkaredisexample;

import ktds.fresh.kafkaredisexample.kafka.Receiver.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaRedisExampleApplication {

    @Autowired
    Receiver receiver;

    public static void main(String[] args) {
        SpringApplication.run(KafkaRedisExampleApplication.class, args);
    }
}
