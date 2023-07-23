package inu.thebite.umul.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Children extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_id")
    private Long id;

    private String name;

    private String birth;

    private String gender;

    private float height;

    private float weight;

    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_number")
    private Member member;

   public static Children createChildren(String name, String birth, String gender, float height, float weight, String memo, Member member) {
       Children children = new Children();
       children.name = name;
       children.birth = birth;
       children.gender = gender;
       children.height = height;
       children.weight = weight;
       children.member = member;
       children.memo = memo;
       return children;
   }

   public void updateChildren(float height, float weight, String memo) {
       this.height = height;
       this.weight = weight;
       this.memo = memo;
   }
}
