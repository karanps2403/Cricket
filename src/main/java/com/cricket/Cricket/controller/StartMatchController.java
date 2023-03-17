package com.cricket.Cricket.controller;

import com.cricket.Cricket.dto.GameDataDTO;
import com.cricket.Cricket.model.Matches;
import com.cricket.Cricket.model.Players;
import com.cricket.Cricket.repository.MatchesRepository;
import com.cricket.Cricket.service.StartMatchService;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class StartMatchController {
    @Autowired
    private StartMatchService startMatchService;
    @Autowired
    private MatchesRepository matchesRepository;
    @Autowired
    private GameDataDTO gameDataDTO;
    private Matches matches;
    private Players players;
    @RequestMapping(method = RequestMethod.POST,value = "/start")
    public String set(@RequestBody GameDataDTO gameDataDTO){

        for(int i=gameDataDTO.getTeam1().size();i<11;i++)
        {
            gameDataDTO.getTeam1().add("player"+(i+1));
        }
        for(int i=gameDataDTO.getTeam2().size();i<11;i++)
        {
            gameDataDTO.getTeam2().add("player"+(i+1));
        }
        this.gameDataDTO = gameDataDTO;
        return startMatchService.start(gameDataDTO);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/getMatchDetails/{matchId}")
    public Matches get(@PathVariable String matchId){
        return matchesRepository.findById(matchId).get();
    }


}
