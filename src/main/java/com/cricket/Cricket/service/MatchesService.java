package com.cricket.Cricket.service;

import com.cricket.Cricket.model.Matches;
import com.cricket.Cricket.repository.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchesService {
    @Autowired
    private MatchesRepository matchesRepository;

    public void addMatch(Matches matches){
        matchesRepository.save(matches);
    }
}
