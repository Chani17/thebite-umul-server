package inu.thebite.umul.repository;

import inu.thebite.umul.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {


    Optional<Member> findByPhoneNumber(String phoneNumber);

//    Optional<Member> findByEmail(String email);

    void deleteByPhoneNumber(String phoneNumber);



}
