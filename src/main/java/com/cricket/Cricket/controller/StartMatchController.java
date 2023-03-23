package com.cricket.Cricket.controller;

import com.cricket.Cricket.dto.GameDataDTO;
import com.cricket.Cricket.service.StartMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StartMatchController {
    @Autowired
    private StartMatchService startMatchService;
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
        return startMatchService.start(gameDataDTO);
    }
}
