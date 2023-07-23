package inu.thebite.umul.model.report.weekly;

import java.time.LocalDate;

public interface WeeklyReportResponse {

    LocalDate getDate();

    Float getAvgSuccessCount();
}
