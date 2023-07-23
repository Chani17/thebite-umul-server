package inu.thebite.umul.controller;

import inu.thebite.umul.model.member.MemberResponse;
import inu.thebite.umul.model.member.MemberSaveRequest;
import inu.thebite.umul.model.member.MemberUpdateRequest;
import inu.thebite.umul.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    // 회원 등록
    @PostMapping("members/join")
    public ResponseEntity<MemberResponse> saveMember(@RequestBody MemberSaveRequest memberSaveRequest) {
        MemberResponse memberResponse = memberService.saveMember(memberSaveRequest);
        return ResponseEntity.ok(memberResponse);
    }

    // 회원 정보 수정
    @PatchMapping("{memberNumber}/member")
    public ResponseEntity updateMember(@PathVariable("memberNumber") String memberNumber,
                                       @RequestBody MemberUpdateRequest memberUpdateRequest) {
        MemberResponse memberResponse = memberService.updateMember(memberNumber, memberUpdateRequest);
        return ResponseEntity.ok(memberResponse);
    }

    // 회원 정보 삭제
    @DeleteMapping("{memberNumber}/member")
    public ResponseEntity deleteMember(@PathVariable("memberNumber") String memberNumber) {
        memberService.deleteMember(memberNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 회원 정보 가져오기
    @GetMapping("{memberNumber}/member")
    public ResponseEntity findByMember(@PathVariable("memberNumber") String memberNumber) {
        MemberResponse member = memberService.findByMember(memberNumber);
        return ResponseEntity.ok(member);
    }

    // 회원의 자녀 정보들을 가져오는 로직은 여기에?

}
