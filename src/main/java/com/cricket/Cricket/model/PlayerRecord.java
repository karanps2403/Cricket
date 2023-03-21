package com.cricket.Cricket.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Builder
public class PlayerRecord {
    @Id
    private String machId;
    private int runsScoredInTheMatch;
}
