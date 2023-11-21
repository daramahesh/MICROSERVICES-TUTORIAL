package com.cricket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "crick_match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;
    private String teamHeading;
    private String matchNumberVenue;
    private String batTeam;
    private String batTeamScore;
    private String bowlTeam;
    private String bowlTeamScore;
    private String liveText;
    private String textComplete;
    private String matchLink;
    @Enumerated
    private MatchStatus status;
    private LocalDate date = LocalDate.now();
    public void setMatchStatus() {
        if (textComplete.trim().isBlank()) this.status = MatchStatus.LIVE;
        else this.status = MatchStatus.COMPLETED;
    }
}
