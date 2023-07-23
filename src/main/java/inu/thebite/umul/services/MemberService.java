package inu.thebite.umul.services;

import inu.thebite.umul.domain.Member;
import inu.thebite.umul.model.member.MemberResponse;
import inu.thebite.umul.model.member.MemberSaveRequest;
import inu.thebite.umul.model.member.MemberUpdateRequest;
import inu.thebite.umul.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 정보 저장
    @Transactional
    public MemberResponse saveMember(MemberSaveRequest memberSaveRequest) {

        // 전화번호 중복 검증
        Optional<Member> findMember = memberRepository.findByPhoneNumber(memberSaveRequest.getPhoneNumber());

        if (findMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        Member member = Member.createMember(memberSaveRequest.getPhoneNumber(), memberSaveRequest.getNickname());
        memberRepository.save(member);
        return MemberResponse.memberResponse(member);
    }

    //회원 정보 수정
    @Transactional
    public MemberResponse updateMember(String memberNumber, MemberUpdateRequest memberUpdateRequest) {
        // 회원 존재 여부 검증 -> id or phoneNumber?
        Member findMember = memberRepository.findByPhoneNumber(memberNumber)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        findMember.updateMember(memberUpdateRequest.getNickname());
        return MemberResponse.memberResponse(findMember);
    }

    // 회원 정보 삭제
    public void deleteMember(String memberNumber) {
        // 회원 존재 여부 검증 -> id or phoneNumber?
        if (memberRepository.findByPhoneNumber(memberNumber).isPresent()) {
            memberRepository.deleteByPhoneNumber(memberNumber);
        } else {
            new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }

    // 회원 정보 가져오기
    public MemberResponse findByMember(String memberNumber) {
        // 회원 존재 여부 검증
        Member findMember = memberRepository.findByPhoneNumber(memberNumber)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        return MemberResponse.memberResponse(findMember);
    }
}
