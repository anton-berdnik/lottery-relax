package org.example.service;

import org.example.model.exception.InvalidDataException;
import org.example.model.request.CreateLotteryBallotRequest;

public interface LotteryBallotService {

    Long create(CreateLotteryBallotRequest request) throws InvalidDataException;
}
