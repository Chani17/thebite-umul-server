package inu.thebite.umul.model;


import lombok.Data;
import java.time.LocalDate;

@Data
public class EatingHabitSaveRequest {

    private String date;

    private String slot;

    private int totalTime;

    private int totalCount;

    private int biteCountByMouth;

    private int successCount;

    private int countPerSuccess;

    private int countPerFail;

}
