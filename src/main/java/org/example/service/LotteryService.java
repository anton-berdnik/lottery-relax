package org.example.service;

import org.example.model.LotteryStatus;
import org.example.model.response.FindLotteryResponse;

import java.time.LocalDate;

public interface LotteryService {
    Long create(Integer seed);
    FindLotteryResponse findByFilter(LocalDate finishDate, LotteryStatus status, Integer page, Integer size);
}
