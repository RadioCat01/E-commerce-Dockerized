package com.Ecom.Payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfiguration {

    @Bean
    public NewTopic paymentTopic(){
        return TopicBuilder
                .name("paymentTopic")
                .build();
    }
}
