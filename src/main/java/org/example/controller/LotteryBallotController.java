package org.example.controller;

import org.example.model.exception.InvalidDataException;
import org.example.model.request.CreateLotteryBallotRequest;
import org.example.service.LotteryBallotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lottery-ballot")
public class LotteryBallotController {

    private final LotteryBallotService lotteryBallotService;

    public LotteryBallotController(LotteryBallotService lotteryBallotService) {
        this.lotteryBallotService = lotteryBallotService;
    }

    @PostMapping("/submit")
    public Long submitBallot(@RequestBody CreateLotteryBallotRequest request) throws InvalidDataException {
        return lotteryBallotService.create(request);
    }
}
