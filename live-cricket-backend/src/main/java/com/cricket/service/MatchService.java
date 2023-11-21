package com.cricket.service;

import com.cricket.entity.Match;

import java.util.List;

public interface MatchService {

    List<Match> getLiveMatches();
    List<List<String>> getCWC2023PointTable();
    List<Match> getMatches();

}
