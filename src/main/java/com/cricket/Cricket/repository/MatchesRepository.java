package com.cricket.Cricket.repository;

import com.cricket.Cricket.model.Matches;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface MatchesRepository extends MongoRepository<Matches,String> {
}
