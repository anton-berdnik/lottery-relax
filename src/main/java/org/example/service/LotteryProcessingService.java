package org.example.service;

import java.time.LocalDate;

public interface LotteryProcessingService {
    void processFinishLotteries(LocalDate finishDate);
}
