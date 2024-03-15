package com.example.projet3chatop.repository;

import com.example.projet3chatop.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, String> {

}
