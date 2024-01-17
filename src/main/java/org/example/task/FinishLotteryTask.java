package org.example.task;

import org.example.service.LotteryProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class FinishLotteryTask {

    private final LotteryProcessingService lotteryProcessingService;

    public FinishLotteryTask(LotteryProcessingService lotteryProcessingService) {
        this.lotteryProcessingService = lotteryProcessingService;
    }

    private static final Logger log = LoggerFactory.getLogger(FinishLotteryTask.class);

    @Scheduled(cron = "0 0 0 * * *")
    public void processFinishLotteries() {
        LocalDate finishDate = LocalDate.now().minus(1, ChronoUnit.DAYS);
        log.info("Finishing lotteries for date {}", finishDate);
        lotteryProcessingService.processFinishLotteries(finishDate);
    }
}
