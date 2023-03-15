package com.cricket.Cricket.controller;

import com.cricket.Cricket.dto.GameDataDTO;
import com.cricket.Cricket.service.StartMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StartMatchController {
    @Autowired
    private StartMatchService startMatchService;
    @RequestMapping(method = RequestMethod.POST,value = "/start")
    public String start(@RequestBody GameDataDTO gameDataDTO){
        return startMatchService.start(gameDataDTO);
    }
}
