package inu.thebite.umul.model.report.daily;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyReportTotalCountResponse {

    private String date;

    private int totalCount;

    private String feedback;

    public DailyReportTotalCountResponse(String date, int totalCount, String feedback) {
        this.date = date;
        this.totalCount = totalCount;
        this.feedback = feedback;
    }
}
