package com.cricket.Cricket.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "matches")
public class Matches {
    @Id
    String matchId;
    String team1Name;
    String team2Name;
    int overs;
    int team1Score;
    int team2Score;
    String winningTeam;

}