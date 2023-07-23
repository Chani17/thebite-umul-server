package inu.thebite.umul.model.report.daily;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyReportTotalCountRequest {

    private String date;

    private int totalCount;
}
