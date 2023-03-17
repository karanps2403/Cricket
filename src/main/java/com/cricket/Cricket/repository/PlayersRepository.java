package com.cricket.Cricket.repository;

import com.cricket.Cricket.model.Players;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PlayersRepository extends MongoRepository<Players,String> {
}
