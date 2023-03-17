package com.cricket.Cricket.service;

import com.cricket.Cricket.model.Matches;
import com.cricket.Cricket.repository.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class MatchesService {
    @Autowired
    private MatchesRepository matchesRepository;

    public void addMatch(Matches matches){
        matchesRepository.save(matches);
    }

    public List<Matches> findAllMatches() {
        return matchesRepository.findAll();
    }

    public Matches findMatchById(String matchId){
        return matchesRepository.findById(matchId).get();
    }
    public String deleteMatchById(String matchId){
        matchesRepository.deleteById(matchId);
        return "The match data of id : "+matchId+" is deleted.";
    }

    public String deleteAllMatches(){
        matchesRepository.deleteAll();
        return "History of all matches s deleted.";
    }
}
