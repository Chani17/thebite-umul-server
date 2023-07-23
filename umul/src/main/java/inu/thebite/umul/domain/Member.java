package inu.thebite.umul.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "phone_number")
    private String phoneNumber;

    private String nickname;

    public static Member createMember(String phoneNumber, String nickname) {
        Member member = new Member();
        member.phoneNumber = phoneNumber;
        member.nickname = nickname;
        return member;
    }

    public void updateMember(String nickname) {;
        this.nickname = nickname;
    }
}
