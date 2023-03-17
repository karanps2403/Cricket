package com.cricket.Cricket.repository;

import com.cricket.Cricket.model.Matches;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchesRepository extends MongoRepository<Matches,String> {
}
