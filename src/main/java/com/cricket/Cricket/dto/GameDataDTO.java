package com.cricket.Cricket.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class GameDataDTO {
    private String matchId;
    private String nameTeam1;
    private String nameTeam2;
    private int overs;
    private List<String> team1 = List.of();
    private List<String> team2 = List.of();

}
