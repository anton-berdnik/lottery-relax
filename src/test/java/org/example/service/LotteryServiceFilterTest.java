package org.example.service;

import org.example.model.LotteryStatus;
import org.example.model.dto.FindLotteryDto;
import org.example.model.entity.Lottery;
import org.example.model.entity.LotteryBallot;
import org.example.model.response.FindLotteryResponse;
import org.example.repository.LotteryBallotRepository;
import org.example.repository.LotteryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LotteryServiceFilterTest {

    @Mock
    LotteryRepository lotteryRepository;
    @Mock
    LotteryBallotRepository lotteryBallotRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testFoundFinishedAndActive() {
        LocalDate dateFinish = LocalDate.of(2024, 1, 11);
        List<Lottery> lotteries = List.of(
                new Lottery()
                        .setId(1L)
                        .setDateCreated(LocalDateTime.of(2024, 1, 11, 12, 55))
                        .setDateFinish(dateFinish)
                        .setStatus(LotteryStatus.FINISHED)
                        .setPrize("prize")
                        .setDateUpdated(LocalDateTime.of(2024, 1, 11, 23, 55))
                        .setBallotPrice(100),
                new Lottery()
                        .setId(2L)
                        .setDateCreated(LocalDateTime.of(2024, 1, 11, 12, 55))
                        .setDateFinish(dateFinish)
                        .setStatus(LotteryStatus.ACTIVE)
                        .setPrize("prize")
                        .setDateUpdated(LocalDateTime.of(2024, 1, 11, 23, 55))
                        .setBallotPrice(100)
        );
        LotteryBallot winnerBallot = new LotteryBallot()
                .setId(100L)
                .setLotteryId(1L)
                .setDateCreated(LocalDateTime.of(2024, 1, 11, 17, 55))
                .setUserAccountId(60L);

        when(lotteryRepository.findByFilter(any(), any(), any())).thenReturn(lotteries);
        when(lotteryBallotRepository.findByLotteryIdAndIsWinner(1L, true)).thenReturn(winnerBallot);

        LotteryService lotteryService = new DefaultLotteryService(lotteryRepository, lotteryBallotRepository);
        FindLotteryResponse result = lotteryService.findByFilter(dateFinish, null, 0, 5);
        FindLotteryDto firstLottery = result.getLotteryList().get(0);
        FindLotteryDto secondLottery = result.getLotteryList().get(1);

        assertEquals(1L, firstLottery.getId());
        assertEquals(LotteryStatus.FINISHED, firstLottery.getStatus());
        assertEquals(100L, firstLottery.getWinnerBallotId());
        assertEquals(2L, secondLottery.getId());
        assertEquals(LotteryStatus.ACTIVE, secondLottery.getStatus());
        assertNull(secondLottery.getWinnerBallotId());
        verify(lotteryBallotRepository, times(1)).findByLotteryIdAndIsWinner(any(), any());
    }
}
