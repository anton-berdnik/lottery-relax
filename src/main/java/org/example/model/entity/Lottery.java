package org.example.model.entity;

import jakarta.persistence.*;
import org.example.model.LotteryStatus;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Lottery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime dateCreated;

    private LocalDate dateFinish;

    @Enumerated(EnumType.STRING)
    private LotteryStatus status;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    private Integer ballotPrice;

    private String prize;

    public Long getId() {
        return id;
    }

    public Lottery setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public Lottery setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public Lottery setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
        return this;
    }

    public LotteryStatus getStatus() {
        return status;
    }

    public Lottery setStatus(LotteryStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public Lottery setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public Integer getBallotPrice() {
        return ballotPrice;
    }

    public Lottery setBallotPrice(Integer ballotPrice) {
        this.ballotPrice = ballotPrice;
        return this;
    }

    public String getPrize() {
        return prize;
    }

    public Lottery setPrize(String prize) {
        this.prize = prize;
        return this;
    }
}
