package org.hwskylo.hw1skylo.Scheduler;


import lombok.extern.slf4j.Slf4j;
import org.hwskylo.hw1skylo.Entity.MessageEntity;
import org.hwskylo.hw1skylo.Enum.MessageStatus;
import org.hwskylo.hw1skylo.Repository.MessageRepository;
import org.hwskylo.hw1skylo.Service.MicroService2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RetryScheduler {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MicroService2Client microService2Client;

    @Scheduled(fixedDelay = 10000)
    public void retryFailedMessages() {

        List<MessageEntity> failedMessages = messageRepository.findByStatus((MessageStatus.FAILED).toString());
        for(MessageEntity msg : failedMessages) {
            log.info("Retrying message with ID: {}, retryCount={}", msg.getId(), msg.getRetryCount());
            boolean success = microService2Client.sendDataToMicroService2(msg);
            if(success) {
                msg.setStatus((MessageStatus.DELIVERED).toString());
            }
            else {
                msg.setRetryCount(msg.getRetryCount() + 1);
            }

            messageRepository.save(msg);
        }
    }
}
