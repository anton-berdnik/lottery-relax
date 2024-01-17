package org.example.model.dto;

import org.example.model.LotteryStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FindLotteryDto {

    private Long id;

    private LocalDateTime dateCreated;

    private LocalDate dateFinish;

    private LotteryStatus status;

    private LocalDateTime dateUpdated;

    private Integer ballotPrice;

    private String prize;

    private Long winnerBallotId;

    private LocalDateTime winnerBallotDateCreated;

    public Long getId() {
        return id;
    }

    public FindLotteryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public FindLotteryDto setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public FindLotteryDto setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
        return this;
    }

    public LotteryStatus getStatus() {
        return status;
    }

    public FindLotteryDto setStatus(LotteryStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public FindLotteryDto setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public Integer getBallotPrice() {
        return ballotPrice;
    }

    public FindLotteryDto setBallotPrice(Integer ballotPrice) {
        this.ballotPrice = ballotPrice;
        return this;
    }

    public String getPrize() {
        return prize;
    }

    public FindLotteryDto setPrize(String prize) {
        this.prize = prize;
        return this;
    }

    public Long getWinnerBallotId() {
        return winnerBallotId;
    }

    public FindLotteryDto setWinnerBallotId(Long winnerBallotId) {
        this.winnerBallotId = winnerBallotId;
        return this;
    }

    public LocalDateTime getWinnerBallotDateCreated() {
        return winnerBallotDateCreated;
    }

    public FindLotteryDto setWinnerBallotDateCreated(LocalDateTime winnerBallotDateCreated) {
        this.winnerBallotDateCreated = winnerBallotDateCreated;
        return this;
    }
}
