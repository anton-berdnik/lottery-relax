package org.example.repository;

import org.example.model.LotteryStatus;
import org.example.model.entity.Lottery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Long> {

    List<Lottery> findByDateFinishAndStatus(LocalDate dateFinish, LotteryStatus status);

    @Query(value = """
            select l from Lottery l
            where (:dateFinish is null or l.dateFinish = :dateFinish)
            and (:lotteryStatus is null or l.status = :lotteryStatus)
            order by l.dateFinish desc
            """)
    List<Lottery> findByFilter(@Param("dateFinish")LocalDate dateFinish, @Param("lotteryStatus")LotteryStatus status, Pageable pageable);
}
