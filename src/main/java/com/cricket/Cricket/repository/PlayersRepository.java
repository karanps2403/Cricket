package com.cricket.Cricket.repository;

import com.cricket.Cricket.model.Players;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends MongoRepository<Players,String> {
}
