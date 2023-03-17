package com.cricket.Cricket.controller;

import com.cricket.Cricket.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class PlayersController {
    @Autowired
    private PlayersService playersService;
    
}
