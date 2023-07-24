package inu.thebite.umul.services;


import inu.thebite.umul.domain.Children;
import inu.thebite.umul.domain.EatingHabit;
import inu.thebite.umul.domain.Member;
import inu.thebite.umul.model.EatingHabitSaveRequest;
import inu.thebite.umul.model.report.daily.DailyReportBiteCountByMouthResponse;
import inu.thebite.umul.model.report.daily.DailyReportTotalCountResponse;
import inu.thebite.umul.model.report.daily.DailyReportTotalTimeResponse;
import inu.thebite.umul.repository.ChildrenRepository;
import inu.thebite.umul.repository.EatingHabitRepository;
import inu.thebite.umul.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EatingHabitService {

    private final EatingHabitRepository eatingHabitRepository;
    private final ChildrenRepository childrenRepository;
    private final MemberRepository memberRepository;
    private final FeedBackService feedBackService;


    // 식습관 기록 저장
    @Transactional
    public void saveEatingHabit(String memberNumber, String childName, EatingHabitSaveRequest eatingHabitSaveRequest) {
        // 등록이 되어 있는 회원인지 확인
        Member member = memberRepository.findByPhoneNumber(memberNumber)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        // 등록이 되어 있는 자녀인지 확인
        Children children = childrenRepository.findByNameWithPhoneNumber(childName, memberNumber)
                .orElseThrow(() -> new IllegalStateException("등록되어 있지 않은 자녀입니다."));

        // 해당 자녀의 부모가 맞는지 확인 후 저장 가능하게 하기
        EatingHabit saveEatingHabit = EatingHabit.createEatingHabit(eatingHabitSaveRequest.getDate(), eatingHabitSaveRequest.getSlot(), eatingHabitSaveRequest.getTotalTime(), eatingHabitSaveRequest.getTotalCount(), eatingHabitSaveRequest.getBiteCountByMouth(), eatingHabitSaveRequest.getSuccessCount(), eatingHabitSaveRequest.getCountPerSuccess(), eatingHabitSaveRequest.getCountPerFail(), children);
        eatingHabitRepository.save(saveEatingHabit);

    }


   // 일일레포트 총 저작횟수 정보 가져오기
    public DailyReportTotalCountResponse getDailyReportWithTotalCount(String memberNumber, String childName, String date) {
        // 등록이 되어 있는 자녀인지 확인
        Children children = childrenRepository.findByNameWithPhoneNumber(childName, memberNumber)
                .orElseThrow(() -> new IllegalStateException("등록되어 있지 않은 자녀입니다."));

        // 해당 날짜에 기록이 있는지 확인
        if (eatingHabitRepository.existsByDate(date)) {
            // 가장 최신 식사 기록 가져오기
            List<Integer> result = eatingHabitRepository.findTotalCountTopByDateAndChildrenNameOrderByCreatedAtDesc(date, childName, memberNumber);
            String feedback = feedBackService.getTotalCountFeedback(result.get(0), children.getGender());
            return new DailyReportTotalCountResponse(date, result.get(0), feedback);
        } else {
            throw new IllegalStateException("해당 날짜에 관한 식사 기록이 존재하지 않습니다.");
        }

    }


    // 일일레포트 총 식사시간 정보 가져오기
    public DailyReportTotalTimeResponse getDailyReportWithTotalTime(String memberNumber, String childName, String date) {
        // 등록이 되어 있는 자녀인지 확인
        Children children = childrenRepository.findByNameWithPhoneNumber(childName, memberNumber)
                .orElseThrow(() -> new IllegalStateException("등록되어 있지 않은 자녀입니다."));

        // 해당 날짜에 기록이 있는지 확인
        if(eatingHabitRepository.existsByDate(date)) {
            // 가장 최신 식사 기록 가져오기
            List<Integer> result = eatingHabitRepository.findTotalTimeTopByDateAndChildrenNameOrderByCreatedAtDesc(date, childName, memberNumber);
            String feedback = feedBackService.getTotalTimeFeedback(result.get(0), children.getGender());
            return new DailyReportTotalTimeResponse(date, result.get(0), feedback);
        } else {
            throw new IllegalStateException("해당 날짜에 관한 식사 기록이 존재하지 않습니다.");
        }

    }


    // 일일레포트 한 입당 저작횟수 정보 가져오기
    public DailyReportBiteCountByMouthResponse getDailyReportWithBiteCountByMouth(String memberNumber, String childName, String date) {
        // 등록이 되어 있는 자녀인지 확인
        childrenRepository.findByNameWithPhoneNumber(childName, memberNumber)
                .orElseThrow(() -> new IllegalStateException("등록되어 있지 않은 자녀입니다."));

        // 해당 날짜에 기록이 있는지 확인
        if(eatingHabitRepository.existsByDate(date)) {
            // 가장 최신 식사 기록 가져오기
            List<Integer> result = eatingHabitRepository.findBitCountWithMouthTopByDateAndChildrenNameOrderByCreatedAtDesc(date, childName, memberNumber);
            String feedback = feedBackService.getBiteCountByMouthFeedback(result.get(0));
            return new DailyReportBiteCountByMouthResponse(date, result.get(0), feedback);
        } else {
            throw new IllegalStateException("해당 날짜에 관한 식사 기록이 존재하지 않습니다.");
        }

    }


    // 주간레포트
//    public List<WeeklyReportResponse> getWeeklyReport(Long childrenId) {
//
//        LocalDate startDay = LocalDate.now();
//        LocalDate endDay = LocalDate.now();
//
//        // 등록이 되어 있는 자녀인지 확인
//        childrenRepository.findById(childrenId)
//                .orElseThrow(() -> new IllegalStateException("등록되어 있지 않은 자녀입니다."));
//
//
//        // request 받은 날짜 기준 한 주 between value 구하기
//        int day = LocalDate.now().getDayOfWeek().getValue();
//
//        switch(day) {
//            // 월요일
//            case 1:
//                endDay = endDay.plusDays(6);
//                break;
//            // 화요일
//            case 2:
//                startDay = startDay.minusDays(1);
//                endDay = endDay.plusDays(5);
//                break;
//            // 수요일
//            case 3:
//                startDay = startDay.minusDays(2);
//                endDay = endDay.plusDays(4);
//                break;
//            // 목요일
//            case 4:
//                startDay = startDay.minusDays(3);
//                endDay = endDay.plusDays(3);
//                break;
//            // 금요일
//            case 5:
//                startDay = startDay.minusDays(4);
//                endDay = endDay.plusDays(2);
//                break;
//            // 토요일
//            case 6:
//                startDay = startDay.minusDays(5);
//                endDay = endDay.plusDays(1);
//                break;
//            // 일요일
//            case 7:
//                startDay = startDay.minusDays(6);
//                break;
//            default:
//                break;
//        }
//
//        // 해당 날짜에 대한 모든 성공횟수 평균 기록들 가져오기
//        List<WeeklyReportResponse> result = eatingHabitRepository.findAllByDateBetweenAndChildrenId(startDay, endDay, childrenId);
//
//        return result;
//    }
}
