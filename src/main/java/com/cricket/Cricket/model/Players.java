package com.cricket.Cricket.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
@Data
@Builder
@Document
public class Players {
    @Id
    private PlayerId playerId;
    private int highScore;
    private ArrayList<Integer> lastMatchRuns;
}
