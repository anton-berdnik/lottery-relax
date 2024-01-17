package org.example.controller;

import org.example.model.LotteryStatus;
import org.example.model.response.FindLotteryResponse;
import org.example.service.LotteryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/lottery")
public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/find-by-filter")
    public FindLotteryResponse findByFilter(
            @RequestParam(required = false) LocalDate finishDate,
            @RequestParam(required = false) LotteryStatus status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {
        return lotteryService.findByFilter(finishDate, status, page, size);
    }
}
