package inu.thebite.umul.domain;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TinyYam extends BaseEntity {

    @Id
    @Column(name = "serial_number")
    private String serial_number;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_number")
    private Member member;

    public static TinyYam createTinyYam(String serial_number, String name, Member member) {
        TinyYam tinyYam = new TinyYam();
        tinyYam.serial_number = serial_number;
        tinyYam.name = name;
        tinyYam.member = member;
        return tinyYam;
    }
}
