package org.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class LotteryBallot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long lotteryId;

    private Long userAccountId;

    @CreatedDate
    private LocalDateTime dateCreated;

    private Boolean isWinner;

    public Long getId() {
        return id;
    }

    public LotteryBallot setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public LotteryBallot setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
        return this;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public LotteryBallot setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LotteryBallot setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Boolean getWinner() {
        return isWinner;
    }

    public LotteryBallot setWinner(Boolean winner) {
        isWinner = winner;
        return this;
    }
}
