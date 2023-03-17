package com.cricket.Cricket.controller;

import com.cricket.Cricket.model.Matches;
import com.cricket.Cricket.service.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
public class MatchesController {
    @Autowired
    private MatchesService matchesService;

    @GetMapping("/matches")
    public List<Matches> getAllMatches(){
        return matchesService.findAllMatches();
    }

    @GetMapping("/matches/{matchId}")
    public Object getMatchById(@PathVariable String matchId){
        return matchesService.findMatchById(matchId);
    }

    @DeleteMapping("/matches")
    public String removeMatches(){
        return matchesService.deleteAllMatches();
    }

    @DeleteMapping("/matches/{matchId}")
    public String removeMatchById(@PathVariable String matchId){
        return matchesService.deleteMatchById(matchId);
    }
}
