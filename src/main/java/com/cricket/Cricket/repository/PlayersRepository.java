package com.cricket.Cricket.repository;

import com.cricket.Cricket.model.Players;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface PlayersRepository extends MongoRepository<Players,String> {
    public List<Players> findAllByMatchId(String matchId);

    public List<Players> findAllByTeamName(String teamName);

    public List<Players> findAllByPlayerName(String playerName);

    public Players findByPlayerNameAndTeamName(String playerName, String TeamName);
}
