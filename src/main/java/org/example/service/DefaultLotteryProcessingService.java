package org.example.service;

import org.example.model.entity.Lottery;
import org.example.model.entity.LotteryBallot;
import org.example.repository.LotteryBallotRepository;
import org.example.repository.LotteryRepository;
import org.example.task.GenerateLotteryTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.example.model.LotteryStatus.*;

@Service
public class DefaultLotteryProcessingService implements LotteryProcessingService {

    private static final Logger log = LoggerFactory.getLogger(GenerateLotteryTask.class);
    private static final Random random = new Random();
    private final LotteryRepository lotteryRepository;
    private final LotteryBallotRepository lotteryBallotRepository;

    public DefaultLotteryProcessingService(LotteryRepository lotteryRepository, LotteryBallotRepository lotteryBallotRepository) {
        this.lotteryRepository = lotteryRepository;
        this.lotteryBallotRepository = lotteryBallotRepository;
    }

    @Override
    public void processFinishLotteries(LocalDate finishDate) {
        List<Lottery> lotteries = lotteryRepository.findByDateFinishAndStatus(finishDate, ACTIVE);
        log.info("Found {} lotteries for date to finish {}", lotteries.size(), finishDate);
        if (lotteries.size() > 0) {
            finishLotteries(lotteries);
            lotteries.forEach(this::selectWinner);
        }
    }

    private void finishLotteries(List<Lottery> lotteries) {
        lotteries.forEach(lottery -> {
            lottery.setStatus(FINISHED);
        });
        lotteryRepository.saveAll(lotteries);
    }

    private void selectWinner(Lottery lottery) {
        List<LotteryBallot> ballots = lotteryBallotRepository.findByLotteryId(lottery.getId());
        if (ballots.size() > 0) {
            LotteryBallot winner = ballots.get(random.nextInt(ballots.size()));
            winner.setWinner(true);
            log.info("Selected winner ballot with id {} for lottery {} (out of {} ballots)",
                    winner.getId(), lottery.getId(), ballots.size());
            lotteryBallotRepository.save(winner);
        } else {
            log.info("No ballots were submitted for lottery with id {}", lottery.getId());
            lottery.setStatus(FINISHED_NO_WINNER);
            lotteryRepository.save(lottery);
        }
    }
}
