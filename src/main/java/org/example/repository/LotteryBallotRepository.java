package org.example.repository;

import org.example.model.entity.LotteryBallot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryBallotRepository extends JpaRepository<LotteryBallot, Long> {

    List<LotteryBallot> findByLotteryId(Long lotteryId);
    LotteryBallot findByLotteryIdAndIsWinner(Long lotteryId, Boolean isWinner);

}
