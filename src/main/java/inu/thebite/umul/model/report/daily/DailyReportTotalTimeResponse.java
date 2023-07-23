package inu.thebite.umul.model.report.daily;


import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyReportTotalTimeResponse {

    private String date;

    private int totalTime;

    private String feedback;

    public DailyReportTotalTimeResponse(String date, int totalTime, String feedback) {
        this.date = date;
        this.totalTime = totalTime;
        this.feedback = feedback;
    }
}
