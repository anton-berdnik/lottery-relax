package org.example.model.response;

import org.example.model.dto.FindLotteryDto;

import java.util.List;

public class FindLotteryResponse {
    private List<FindLotteryDto> lotteryList;

    public FindLotteryResponse() {
    }

    public FindLotteryResponse(List<FindLotteryDto> lotteryList) {
        this.lotteryList = lotteryList;
    }

    public List<FindLotteryDto> getLotteryList() {
        return lotteryList;
    }

    public FindLotteryResponse setLotteryList(List<FindLotteryDto> lotteryList) {
        this.lotteryList = lotteryList;
        return this;
    }
}
