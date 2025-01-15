package org.hwskylo.hw1skylo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @Autowired
    private MessageService messageService;

    @KafkaListener(topics = "retry-topic", groupId="ms1-container-group")
    public void consume(String messagePayload) {
        messageService.saveAndSend(messagePayload);
    }
}
