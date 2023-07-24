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

//    @Query(value = "SELECT e.total_count FROM eating_habit e WHERE e.child_id = (SELECT c.child_id FROM children c WHERE c.name = :childName AND c.phone_number = : phoneNumber) AND e.date = :date ORDER BY e.created_at DESC LIMIT 1 ", nativeQuery = true)
//    List<Integer> findTotalCountTopByDateAndChildrenNameOrderByCreatedAtDesc(String date, String childName, String phoneNumber);

    @Query(value = "SELECT e.total_count FROM eating_habit e WHERE e.child_id = (SELECT c.child_id FROM children c WHERE c.name = :childName AND c.phone_number = :phoneNumber) AND e.date = :date ORDER BY e.created_at DESC LIMIT 1", nativeQuery = true)
    List<Integer> findTotalCountTopByDateAndChildrenNameOrderByCreatedAtDesc(@Param("date") String date, @Param("childName") String childName, @Param("phoneNumber") String phoneNumber);
    @Query(value = "SELECT e.total_time FROM eating_habit e WHERE e.child_id = (SELECT c.child_id FROM children c WHERE c.name = :childName AND c.phone_number = : phoneNumber) AND e.date = :date ORDER BY e.created_at DESC LIMIT 1 ", nativeQuery = true)
    List<Integer> findTotalTimeTopByDateAndChildrenNameOrderByCreatedAtDesc(@Param("date") String date, @Param("childName") String childName, @Param("phoneNumber")String phoneNumber);

    @Query(value = "SELECT e.bite_count_with_mouth FROM eating_habit e WHERE e.child_id = (SELECT c.child_id FROM children c WHERE c.name = :childName AND c.phone_number = : phoneNumber) AND e.date = :date ORDER BY e.created_at DESC LIMIT 1", nativeQuery = true)
    List<Integer> findBitCountWithMouthTopByDateAndChildrenNameOrderByCreatedAtDesc(@Param("date")String date, @Param("childName")String childName, @Param("phoneNumber")String phoneNumber);

//    @Query(value = "SELECT count_per_success FROM eating_habit WHERE name = :childName AND date = :date ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
//    List<Integer> findCountPerSuccessByDateAndChildrenNameOrderByCreatedAtDesc(String date, String childName);
//
//    @Query(value = "SELECT count_per_fail FROM eating_habit WHERE name = :childName AND date = :date ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
//    List<Integer> findCountPerFailByDateAndChildrenNameOrderByCreatedAtDesc(String date, String childName);

    @Query(value = "SELECT date, AVG(success_count) AS avgSuccessCount FROM eating_habit WHERE name = :childName AND date >= :startDate AND date <= :endDate GROUP BY date", nativeQuery = true)
    List<WeeklyReportResponse> findAllByDateBetweenAndChildrenId(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, String childName);

}
