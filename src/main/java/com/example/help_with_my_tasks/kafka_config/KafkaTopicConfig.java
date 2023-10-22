package com.example.help_with_my_tasks.kafka_config;

import com.example.help_with_my_tasks.constants.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createTopic(){
        return TopicBuilder
                .name(KafkaConstants.BOOKING_TOPIC_NAME)
                .partitions(2)
                .build();
    }
}
