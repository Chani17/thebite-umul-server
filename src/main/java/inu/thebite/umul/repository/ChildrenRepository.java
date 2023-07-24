package inu.thebite.umul.repository;

import inu.thebite.umul.domain.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChildrenRepository extends JpaRepository<Children, Long> {

    Optional<Children> findByName(String name);

    @Query(value = "SELECT * FROM children WHERE phone_number = :memberNumber AND name = :childName", nativeQuery = true)
    Optional<Children> findByNameWithPhoneNumber(String childName, String memberNumber);

    @Query(value = "SELECT phone_number FROM children WHERE phone_number = :phoneNumber and name = :childName", nativeQuery = true)
    String findByPhoneNumber(String phoneNumber, String childName);

    @Query(value = "SELECT * FROM children WHERE phone_number = :phoneNumber", nativeQuery = true)
    List<Children> findChildrenByPhoneNumber(String phoneNumber);
}
