package org.example.service;

import org.example.model.LotteryStatus;
import org.example.model.dto.FindLotteryDto;
import org.example.model.entity.Lottery;
import org.example.model.entity.LotteryBallot;
import org.example.model.response.FindLotteryResponse;
import org.example.repository.LotteryBallotRepository;
import org.example.repository.LotteryRepository;
import org.example.task.GenerateLotteryTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.example.model.LotteryStatus.FINISHED;

@Service
public class DefaultLotteryService implements LotteryService {

    private static final Logger log = LoggerFactory.getLogger(GenerateLotteryTask.class);
    private static final List<String> prizes = List.of("car", "idea", "100$", "bike", "phone");
    private final LotteryRepository lotteryRepository;
    private final LotteryBallotRepository lotteryBallotRepository;

    public DefaultLotteryService(LotteryRepository lotteryRepository, LotteryBallotRepository lotteryBallotRepository) {
        this.lotteryRepository = lotteryRepository;
        this.lotteryBallotRepository = lotteryBallotRepository;
    }

    @Override
    public Long create(Integer seed) {
        log.info("Creating mock lottery");
        Lottery lottery = new Lottery()
                .setBallotPrice((seed + 1) * 10)
                .setPrize(prizes.get(seed))
                .setDateFinish(LocalDate.now().plusDays(seed))
                .setStatus(LotteryStatus.ACTIVE)
                .setDateCreated(LocalDateTime.now());
        return lotteryRepository.save(lottery).getId();
    }

    @Override
    public FindLotteryResponse findByFilter(LocalDate finishDate, LotteryStatus status, Integer page, Integer size) {
        List<FindLotteryDto> lotteries = lotteryRepository.findByFilter(finishDate, status, PageRequest.of(page, size)).stream()
                .map(this::mapFindLotteryDto)
                .map(this::addWinnerInfo)
                .toList();
        return new FindLotteryResponse(lotteries);
    }

    private FindLotteryDto mapFindLotteryDto(Lottery lottery) {
        return new FindLotteryDto()
                .setId(lottery.getId())
                .setDateCreated(lottery.getDateCreated())
                .setDateFinish(lottery.getDateFinish())
                .setStatus(lottery.getStatus())
                .setDateUpdated(lottery.getDateUpdated())
                .setBallotPrice(lottery.getBallotPrice())
                .setPrize(lottery.getPrize());
    }

    private FindLotteryDto addWinnerInfo(FindLotteryDto lottery) {
        if (FINISHED.equals(lottery.getStatus())) {
            LotteryBallot winner = lotteryBallotRepository.findByLotteryIdAndIsWinner(lottery.getId(), true);
            lottery.setWinnerBallotId(winner.getId())
                    .setWinnerBallotDateCreated(winner.getDateCreated());
        }
        return lottery;
    }
}
