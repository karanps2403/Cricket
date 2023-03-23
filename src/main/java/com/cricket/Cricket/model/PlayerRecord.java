package com.cricket.Cricket.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerRecord {
    private String machId;
    private int runsScoredInTheMatch;
    int numberOfFours;
    int numberOfSixes;
    int numberOfBallsFaced;
}
