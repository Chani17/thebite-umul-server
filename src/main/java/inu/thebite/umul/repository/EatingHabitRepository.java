package inu.thebite.umul.repository;

import inu.thebite.umul.domain.EatingHabit;
import inu.thebite.umul.model.report.weekly.WeeklyReportResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EatingHabitRepository extends JpaRepository<EatingHabit, Long> {


    boolean existsByDate(String date);

    @Query(value = "SELECT total_count FROM eating_habit WHERE child_id = :(SELECT child_id FROM children WHERE name = :childName) AND date = :date ORDER BY e.created_at DESC LIMIT 1 ", nativeQuery = true)
    List<Integer> findTotalCountTopByDateAndChildrenNameOrderByCreatedAtDesc(String date, String childName);

    @Query(value = "SELECT total_time FROM eating_habit WHERE child_id = :(SELECT child_id FROM children WHERE name = :childName) AND date = :date ORDER BY e.created_at DESC LIMIT 1 ", nativeQuery = true)
    List<Integer> findTotalTimeTopByDateAndChildrenNameOrderByCreatedAtDesc(String date, String childName);

    @Query(value = "SELECT bite_count_with_mouth FROM eating_habit WHERE child_id = :(SELECT child_id FROM children WHERE name = :childName) AND date = :date ORDER BY e.created_at DESC LIMIT 1", nativeQuery = true)
    List<Integer> findBitCountWithMouthTopByDateAndChildrenNameOrderByCreatedAtDesc(String date, String childName);

//    @Query(value = "SELECT count_per_success FROM eating_habit WHERE name = :childName AND date = :date ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
//    List<Integer> findCountPerSuccessByDateAndChildrenNameOrderByCreatedAtDesc(String date, String childName);
//
//    @Query(value = "SELECT count_per_fail FROM eating_habit WHERE name = :childName AND date = :date ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
//    List<Integer> findCountPerFailByDateAndChildrenNameOrderByCreatedAtDesc(String date, String childName);

    @Query(value = "SELECT date, AVG(success_count) AS avgSuccessCount FROM eating_habit WHERE name = :childName AND date >= :startDate AND date <= :endDate GROUP BY date", nativeQuery = true)
    List<WeeklyReportResponse> findAllByDateBetweenAndChildrenId(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, String childName);

}
