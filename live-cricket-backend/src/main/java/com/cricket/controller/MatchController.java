package com.cricket.controller;

import com.cricket.entity.Match;
import com.cricket.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("match/")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("live-matches")
    public ResponseEntity<List<Match>> getLiveMatches() {
        return new ResponseEntity<>(this.matchService.getLiveMatches(), HttpStatus.OK);
    }

    @GetMapping("/point-table")
    public ResponseEntity<?> getCWC2023PointTable() {
        return new ResponseEntity<>(this.matchService.getCWC2023PointTable(), HttpStatus.OK);
    }
    @GetMapping("all-matches")
    public ResponseEntity<List<Match>> getMatches() {
        return new ResponseEntity<>(this.matchService.getMatches(), HttpStatus.OK);
    }
}
