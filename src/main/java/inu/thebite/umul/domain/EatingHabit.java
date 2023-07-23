package inu.thebite.umul.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EatingHabit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EatingHabit_id")
    private Long id;

    private String date;

    private String slot;

    private int totalTime;

    private int totalCount;

    private int biteCountWithMouth;

    private int successCount;

    private int countPerSuccess;

    private int countPerFail;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Children children;

    public static EatingHabit createEatingHabit(String date, String slot, int totalTime, int totalCount, int mouthCount, int successCount, int countPerSuccess, int countPerFail, Children children) {
        EatingHabit eatingHabit = new EatingHabit();
        eatingHabit.date = date;
        eatingHabit.slot = slot;
        eatingHabit.totalTime = totalTime;
        eatingHabit.totalCount = totalCount;
        eatingHabit.biteCountWithMouth = mouthCount;
        eatingHabit.successCount = successCount;
        eatingHabit.countPerSuccess = countPerSuccess;
        eatingHabit.countPerFail = countPerFail;
        eatingHabit.children = children;
        return eatingHabit;
    }
}
