package org.example.model.request;

public class CreateLotteryBallotRequest {

    private Long userAccountId;

    private Long lotteryId;

    public Long getUserAccountId() {
        return userAccountId;
    }

    public Long getLotteryId() {
        return lotteryId;
    }
}
