package org.hwskylo.hw1skylo.Repository;

import org.hwskylo.hw1skylo.Entity.MessageEntity;
import org.hwskylo.hw1skylo.Enum.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
    List<MessageEntity> findByStatus(String status);
}
