package org.example.orderservice.config

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.example.orderservice.dto.event.OrderEvent
import org.example.orderservice.util.Constants.ORDER_CREATED
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: List<String>,
) {

    @Bean
    fun orderCreatedProducerFactory(): ProducerFactory<String, OrderEvent> {
        val properties = HashMap<String, Any>()
        properties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers[0]
        properties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        properties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java

        return DefaultKafkaProducerFactory(properties)
    }

    @Bean
    fun orderCreatedKafkaTemplate(): KafkaTemplate<String, OrderEvent> {
        return KafkaTemplate(orderCreatedProducerFactory())
    }

    @Bean
    fun orderCreatedTopic(): NewTopic {
        return NewTopic(ORDER_CREATED, 2, 2)
    }
}
