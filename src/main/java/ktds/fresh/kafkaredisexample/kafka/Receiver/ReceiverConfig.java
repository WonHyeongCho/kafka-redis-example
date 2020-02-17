package ktds.fresh.kafkaredisexample.kafka.Receiver;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

public class ReceiverConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String brokers;

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        // 컨슈머 설정
        factory.setConsumerFactory(consumerFactory());
        // 컨슈머 thread 개수
        factory.setConcurrency(3);
        // poll Time
        factory.getContainerProperties().setPollTimeout(5000);
        // Listener 등록
        factory.getContainerProperties().setMessageListener(getReceiver());

        return factory;
    }

    private Receiver getReceiver(){
        return new Receiver();
    }

    private ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(getProperties());
    }

    private Map<String, Object> getProperties(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        return properties;
    }
}
