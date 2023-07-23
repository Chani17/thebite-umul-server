package inu.thebite.umul.services;


import inu.thebite.umul.domain.Member;
import inu.thebite.umul.domain.TinyYam;
import inu.thebite.umul.model.TinyYamRegisterRequest;
import inu.thebite.umul.repository.MemberRepository;
import inu.thebite.umul.repository.TinyYamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TinyYamService {

    private final TinyYamRepository tinyYamRepository;
    private final MemberRepository memberRepository;

    public TinyYam registerTinyYam(String memberNumber, TinyYamRegisterRequest tinyYamRegisterRequest) {
        // 회원 존재 여부 확인
        Member member = memberRepository.findByPhoneNumber(memberNumber)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        TinyYam tinyYam = TinyYam.createTinyYam(tinyYamRegisterRequest.getSerial_number(), tinyYamRegisterRequest.getName(), member);
        tinyYamRepository.save(tinyYam);
        return tinyYam;
    }
}
