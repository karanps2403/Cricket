package com.cricket.Cricket.controller;

import com.cricket.Cricket.model.Players;
import com.cricket.Cricket.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
public class PlayersController {
    @Autowired
    private PlayersService playersService;

    @GetMapping("/findByPlayerName/{playerName}")
    public List<Players> findAllByPlayerName(@PathVariable String playerName){
        return playersService.findAllByPlayerName(playerName);
    }
    @GetMapping("/findAllByTeamName/{teamName}")
    public List<Players> findAllByTeamName(@PathVariable String teamName){
        return playersService.findAllByTeamName(teamName);
    }
    @GetMapping("/findAllByMatchId/{matchId}")
    public List<Players> findAllByMatchId(@PathVariable String matchId){
        return playersService.findAllByMatchId(matchId);
    }
    @GetMapping("/findByPlayerNameAndTeamName/{playerName}/{teamName}")
    public Players findByPlayerNameAndTeamName(@PathVariable String playerName, @PathVariable String teamName){
        return playersService.findByPlayerNameAndTeamName(playerName,teamName);
    }

    
}
