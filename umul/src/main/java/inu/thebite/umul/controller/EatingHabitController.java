package inu.thebite.umul.controller;


import inu.thebite.umul.model.EatingHabitSaveRequest;
import inu.thebite.umul.model.report.daily.DailyReportBiteCountByMouthResponse;
import inu.thebite.umul.model.report.daily.DailyReportTotalCountResponse;
import inu.thebite.umul.model.report.daily.DailyReportTotalTimeResponse;
import inu.thebite.umul.model.report.weekly.WeeklyReportResponse;
import inu.thebite.umul.services.EatingHabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class EatingHabitController {

    private final EatingHabitService eatingHabitService;

    // 식습관 기록 저장
    @PostMapping("{memberNumber}/{childName}/save")
    public ResponseEntity saveEatingHabit(@PathVariable("childName") String childName,
                                          @PathVariable("memberNumber") String memberNumber,
                                          @RequestBody EatingHabitSaveRequest eatingHabitSaveRequest) {
        eatingHabitService.saveEatingHabit(memberNumber, childName, eatingHabitSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 일일레포트 총 저작횟수 정보 가져오기
    @GetMapping("{childName}/dailyReport/totalCount")
    public ResponseEntity getDailyReportWithTotalCount(@PathVariable String childName,
                                                      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date)  {
        DailyReportTotalCountResponse response = eatingHabitService.getDailyReportWithTotalCount(childName, date);
        return ResponseEntity.ok(response);
    }

    // 일일레포트 총 식사시간 정보 가져오기
    @GetMapping("{childName}/dailyReport/totalTime")
    public ResponseEntity getDailyReportWithTotalTime(@PathVariable String childName,
                                                      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date) {
        DailyReportTotalTimeResponse response = eatingHabitService.getDailyReportWithTotalTime(childName, date);
        return ResponseEntity.ok(response);
    }

    // 일일레포트 한 입당 저작횟수 정보 가져오기
    @GetMapping("{childName}/dailyReport/biteCountByMouth")
    public ResponseEntity getDailyReportWithBiteCountByMouth(@PathVariable String childName,
                                                             @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date) {
        DailyReportBiteCountByMouthResponse response = eatingHabitService.getDailyReportWithBiteCountByMouth(childName, date);
        return ResponseEntity.ok(response);
    }

    // 주간레포트
//    @GetMapping("{childrenId}/weeklyReport")
//    public ResponseEntity getWeeklyReport(@PathVariable Long childrenId) {
//        List<WeeklyReportResponse> result = eatingHabitService.getWeeklyReport(childrenId);
//        return ResponseEntity.ok(result);
//    }

}
