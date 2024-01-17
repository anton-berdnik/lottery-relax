package org.example.service;

import org.example.model.LotteryStatus;
import org.example.model.entity.LotteryBallot;
import org.example.model.exception.InvalidDataException;
import org.example.model.request.CreateLotteryBallotRequest;
import org.example.repository.LotteryBallotRepository;
import org.example.repository.LotteryRepository;
import org.example.repository.UserAccountRepository;
import org.example.task.GenerateLotteryTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DefaultLotteryBallotService implements LotteryBallotService {

    private static final Logger log = LoggerFactory.getLogger(GenerateLotteryTask.class);
    private final UserAccountRepository userAccountRepository;
    private final LotteryRepository lotteryRepository;
    private final LotteryBallotRepository lotteryBallotRepository;

    public DefaultLotteryBallotService(UserAccountRepository userAccountRepository, LotteryRepository lotteryRepository, LotteryBallotRepository lotteryBallotRepository) {
        this.userAccountRepository = userAccountRepository;
        this.lotteryRepository = lotteryRepository;
        this.lotteryBallotRepository = lotteryBallotRepository;
    }

    @Override
    public Long create(CreateLotteryBallotRequest request) throws InvalidDataException {
        validateCreateRequest(request);
        log.info("Submitting a ballot for user {} for lottery {}", request.getUserAccountId(), request.getLotteryId());
        return lotteryBallotRepository.save(
                        new LotteryBallot()
                                .setUserAccountId(request.getUserAccountId())
                                .setLotteryId(request.getLotteryId())
                                .setDateCreated(LocalDateTime.now())
                                .setWinner(false))
                .getId();
    }

    private void validateCreateRequest(CreateLotteryBallotRequest request) throws InvalidDataException {
        if (request.getUserAccountId() == null || request.getLotteryId() == null) {
            throw new InvalidDataException("Incorrect data");
        }
        if (!userAccountRepository.existsById(request.getUserAccountId())) {
            throw new InvalidDataException("User is not present");
        }
        lotteryRepository.findById(request.getLotteryId())
                .filter(lottery -> LotteryStatus.ACTIVE.equals(lottery.getStatus()))
                .orElseThrow(() -> new InvalidDataException("Active lottery not found"));
    }
}
