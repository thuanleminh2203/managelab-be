package com.example.demo.repository;

import com.example.demo.dto.MessageResponseDTO;
import com.example.demo.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    @Query(value = "SELECT * from message m where m.id_send in (?1,?2) or m.id_receive in (?1,?2) order by m.message_id asc ", nativeQuery = true)
    List<MessageEntity> getMess(int myId, int yourId);
}
