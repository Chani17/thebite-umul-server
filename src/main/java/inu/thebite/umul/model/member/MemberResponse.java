package inu.thebite.umul.model.member;

import inu.thebite.umul.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private String phoneNumber;

    private String nickname;

    public static MemberResponse memberResponse(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.phoneNumber = member.getPhoneNumber();
        memberResponse.nickname = member.getNickname();
        return memberResponse;
    }
}
