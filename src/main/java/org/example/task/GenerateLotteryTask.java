package org.example.task;

import org.example.service.DefaultLotteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateLotteryTask {

    private static final Logger log = LoggerFactory.getLogger(GenerateLotteryTask.class);
    private final DefaultLotteryService lotteryService;

    public GenerateLotteryTask(DefaultLotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @Scheduled(fixedRate = 5000)
    public void generate() {
        randomCheckGenerateLottery();
    }

    private void randomCheckGenerateLottery() {
        Random rand = new Random();
        int i = rand.nextInt(10000);
        if (i < 5) {
            Long lotteryId = lotteryService.create(i);
            log.info("Created lottery with id {}", lotteryId);
        }
    }
}
