package org.hwskylo.hw1skylo.Service;


import org.apache.kafka.common.protocol.Message;
import org.hwskylo.hw1skylo.Entity.MessageEntity;
import org.hwskylo.hw1skylo.Enum.MessageStatus;
import org.hwskylo.hw1skylo.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MicroService2Client microService2Client;

    public void saveAndSend(String payload) {
        MessageEntity entity = new MessageEntity();
        entity.setPayload(payload);
        entity.setStatus((MessageStatus.PENDING).toString());
        entity.setRetryCount(0);

        entity = messageRepository.save(entity);

        boolean success = microService2Client.sendDataToMicroService2(entity);

        if(success)
            entity.setStatus((MessageStatus.DELIVERED).toString());
        else
            entity.setStatus((MessageStatus.FAILED).toString());

        messageRepository.save(entity);
    }

}
