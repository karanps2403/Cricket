package com.cricket.Cricket.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@Document(collection = "players")
public class Players {
    @Id
    private String playerId;
    private String playerName;
    private String teamName;
    private int highScore;
//    private ArrayList<PlayerRecord> playerRecordList;
}



