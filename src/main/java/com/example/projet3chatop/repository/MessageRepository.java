package com.example.projet3chatop.repository;

import com.example.projet3chatop.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
