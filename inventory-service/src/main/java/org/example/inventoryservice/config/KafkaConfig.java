package org.example.inventoryservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private List<String> bootstrapServers;

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderCreatedEventConsumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Object> orderCreatedEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerProperties(),
                new StringDeserializer(),
                new JsonDeserializer<>()
        );
    }

    @Bean
    public Map<String, Object> consumerProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(JsonDeserializer.TYPE_MAPPINGS,
                "org.example.orderservice.dto.event.OrderCreatedEvent:org.example.inventoryservice.dto.event.OrderCreatedEvent");
        properties.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return properties;
    }

    @Bean
    public NewTopic inventoryReservedTopic() {
        return new NewTopic("inventory.reserved", 2, (short) 2);
    }

    @Bean
    public NewTopic inventoryRealisedTopic() {
        return new NewTopic("inventory.realised", 2, (short) 2);
    }

    @Bean
    public NewTopic inventoryDeductedTopic() {
        return new NewTopic("inventory.deducted", 2, (short) 2);
    }

    @Bean
    public NewTopic inventoryRestockedTopic() {
        return new NewTopic("inventory.restocked", 2, (short) 2);
    }
}
