package com.cricket.Cricket.service;

import com.cricket.Cricket.model.Matches;
import com.cricket.Cricket.repository.MatchesRepository;
import com.cricket.Cricket.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class MatchesService {
    @Autowired
    private MatchesRepository matchesRepository;
    @Autowired
    private PlayersService playersService;

    public void addMatch(Matches match){
        matchesRepository.save(match);
    }
    public List<Matches> findAllMatches() {
        return matchesRepository.findAll();
    }
    public Object findMatchById(String matchId){
        if(matchesRepository.existsById(matchId))return matchesRepository.findById(matchId).get();
        else return "No match with such id found";
    }
    /** **/
    public String deleteMatchById(String matchId){
        List<String> player=matchesRepository.findById(matchId).get().getPlayerIds();
        for (String s : player) {
            playersService.deletePlayerRecord(s, matchId);
        }
        matchesRepository.deleteById(matchId);
        return "The match data of id : "+matchId+" is deleted.";}
    public String deleteAllMatches(){
        matchesRepository.deleteAll();
        return "History of all matches and players is deleted.";
    }
}
