package com.netpay.kafkaConsumerMicroService.config;

import com.netpay.kafkaConsumerMicroService.model.Category;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {


    @Value( "${kafka.bootstrapAddress}" )
    private String bootstrapAddress; //"127.0.0.1:9092"

    @Value( "${kafka.groupId}" )
    private String groupId; //group_id

    /**
     * Kafka Consumer Factory bean.
     * @return
     */
    @Bean
    ConsumerFactory<String, Category> kafkaConsumerFactory(){

        JsonDeserializer<Category> deserializer = new JsonDeserializer<>(Category.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }


    /**
     * Consumer Kafka Listener Factory.
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Category> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Category> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(kafkaConsumerFactory());
        factory.setBatchListener(true);
        return factory;
    }
}
