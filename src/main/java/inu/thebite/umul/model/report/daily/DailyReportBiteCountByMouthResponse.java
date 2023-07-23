package inu.thebite.umul.model.report.daily;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyReportBiteCountByMouthResponse {

    private String date;

    private int biteCountByMouth;

    private String feedback;

    public DailyReportBiteCountByMouthResponse(String date, int biteCountByMouth, String feedback) {
        this.date = date;
        this.biteCountByMouth = biteCountByMouth;
        this.feedback = feedback;
    }
}
