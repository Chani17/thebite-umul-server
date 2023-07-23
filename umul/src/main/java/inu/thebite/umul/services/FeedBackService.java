package inu.thebite.umul.services;

import org.springframework.stereotype.Service;

@Service
public class FeedBackService {

    private int m_totalCount = 31;
    private int f_totalCount = 51;
    private int m_totalTime = 18 * 60;
    private int f_totalTime = 22 * 60;
    private int countByMouth = 33;

    public String getTotalCountFeedback(int totalCount, String gender) {
        String feedback = "";
        if(gender.equals('M')) {
            if(totalCount > m_totalCount) {
                feedback = "우리아이의 총 저작횟수는 정상군의 총 저작횟수보다 " + (totalCount - m_totalCount) + "회 많아요! 잘하고 있어요!";
            } else if (totalCount < m_totalCount){
                feedback = "우리아이의 총 저작횟수는 정상군의 총 저작횟수보다 " + (m_totalCount - totalCount) + "회 적어요! 조금 더 노력해봐요!";
            } else {
                feedback = "우리아이의 총 저작횟수는 정상군의 총 저작횟수와 동일해요! 잘하고 있어요!";
            }
        } else {
            if(totalCount > f_totalCount) {
                feedback = "우리아이의 총 저작횟수는 정상군의 총 저작횟수보다 " + (totalCount - f_totalCount) + "회 많아요! 잘하고 있어요!";
            } else if (totalCount < f_totalCount){
                feedback = "우리아이의 총 저작횟수는 정상군의 총 저작횟수보다 " + (f_totalCount - totalCount) + "회 많아요! 조금 더 노력해봐요!";
            } else {
                feedback = "우리아이의 총 저작횟수는 정상군의 총 저작횟수와 동일해요! 잘하고 있어요!";
            }
        }

        return feedback;
    }

    public String getTotalTimeFeedback(int totalTime, String gender) {
        String feedback = "";
        if(gender.equals('M')) {
            if(totalTime > m_totalTime){
                feedback = "우리아이 총 식사시간은 정산군의 총 식사시간보다 " + (totalTime - m_totalTime)/60 + "분 " + (totalTime - m_totalTime)%60 + "초 길어요! 잘하고 있어요!";
            }
            else if(totalTime < m_totalTime){
                feedback = "우리아이 총 식사시간은 정산군의 총 식사시간보다 " + (m_totalTime - totalTime)/60 + "분 " + (m_totalTime - totalTime)%60 + "초 짧아요! 조금 더 노력해봐요!";
            } else {
                feedback = "우리아이의 총 식사시간은 정상군의 총 식사시간과 동일해요! 잘하고 있어요!";
            }
        } else {
            if(totalTime > f_totalTime){
                feedback = "우리아이 총 식사시간은 정산군의 총 식사시간보다 " + (totalTime - f_totalTime)/60 + "분 " + (totalTime - f_totalTime)%60 + "초 길어요! 잘하고 있어요!";
            }
            else if(totalTime < f_totalTime){
                feedback = "우리아이 총 식사시간은 정산군의 총 식사시간보다 " + (f_totalTime - totalTime)/60 + "분 " + (f_totalTime - totalTime)%60 + "초 짧아요! 조금 더 노력해봐요!";
            } else {
                feedback = "우리아이의 총 식사시간은 정상군의 총 식사시간과 동일해요! 잘하고 있어요!";
            }
        }

        return feedback;
    }

    public String getBiteCountByMouthFeedback(int BiteCountByMouth) {
        String feedback = "";
        if (BiteCountByMouth > countByMouth){
            feedback = "우리아이의 한 입당 저작횟수는 정상군의 한 입당 저작횟수보다 " + (BiteCountByMouth - countByMouth) + "회 더 많아요! 잘하고 있어요!";
        }
        else if (BiteCountByMouth < countByMouth){
            feedback = "우리아이의 한 입당 저작횟수는 정상군의 한 입당 저작횟수보다 "  +(countByMouth - BiteCountByMouth) + "회 적어요! 조금 더 노력해봐요!";
        } else {
            feedback = "우리아이의 한 입당 저작횟수는 정상군의 한 입당 저작회수와 동일해요! 잘하고 있어요!";
        }
        return feedback;
    }

    public String getBMIFeedback(double bmi) {
        String feedback = "우리 아이의 신체질량지수(BMI)는 " + bmi;
        if (bmi >= 30) {
            feedback += "로 '비만'입니다.";
        } else if (25 <= bmi & bmi <= 29) {
            feedback += "로 '과체중'입니다.";
        } else if (20 <= bmi & bmi <= 24) {
            feedback += "로 '정상'입니다.";
        } else {
            feedback += "로 '저체중'입니다.";
        }
        return feedback;
    }
}
