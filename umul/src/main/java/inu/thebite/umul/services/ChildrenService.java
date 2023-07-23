package inu.thebite.umul.services;


import inu.thebite.umul.domain.Children;
import inu.thebite.umul.domain.Member;
import inu.thebite.umul.model.BMICalculateRequest;
import inu.thebite.umul.model.BMIResponse;
import inu.thebite.umul.model.children.ChildrenResponse;
import inu.thebite.umul.model.children.ChildrenSaveRequest;
import inu.thebite.umul.model.children.ChildrenUpdateRequest;
import inu.thebite.umul.repository.ChildrenRepository;
import inu.thebite.umul.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChildrenService {

    private final ChildrenRepository childrenRepository;
    private final MemberRepository memberRepository;
    private final FeedBackService feedBackService;


    // 자녀 정보 등록
    @Transactional
    public Children saveChildren(String memberNumber, ChildrenSaveRequest childrenSaveRequest) {
        Member member = memberRepository.findById(memberNumber)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        Children children = Children.createChildren(childrenSaveRequest.getName(), childrenSaveRequest.getBirth(), childrenSaveRequest.getGender(), childrenSaveRequest.getHeight(), childrenSaveRequest.getWeight(), childrenSaveRequest.getMemo(), member);
        childrenRepository.save(children);
        return children;
    }

    // 자녀 정보 수정 -> update는 되지만 오류 출력(원인 찾기)
    @Transactional
    public Children updateChildren(String childrenName, ChildrenUpdateRequest childrenUpdateRequest) {
        // 등록된 자녀 유무 확인 과정
        Children children = childrenRepository.findByName(childrenName)
                .orElseThrow(() -> new IllegalArgumentException("해당 자녀가 존재하지 않습니다."));
        children.updateChildren(childrenUpdateRequest.getHeight(), childrenUpdateRequest.getWeight(), childrenUpdateRequest.getMemo());
        return children;
    }

    // 자녀 정보 불러오기
    public ChildrenResponse findByChildren(String childrenName) {
        // 등록된 자녀 유무 확인 과정
        Children children = childrenRepository.findByName(childrenName)
                .orElseThrow(() -> new IllegalArgumentException("해당 자녀의 정보가 존재하지 않습니다."));
        return new ChildrenResponse(children);
    }

    // 자녀 정보 삭제
    public void deleteChildren(String childrenName, String memberNumber) {
        // 등록된 자녀 유무 확인 과정
        Children children = childrenRepository.findByName(childrenName)
                .orElseThrow(() -> new IllegalArgumentException("해당 자녀의 정보가 존재하지 않습니다."));

        memberRepository.findByPhoneNumber(memberNumber)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        // 해당 자녀의 부모가 맞다면 삭제 가능, 아니라면 불가능
        if(children.getMember().getPhoneNumber().equals(memberNumber)) {
            childrenRepository.deleteById(children.getId());
        } else {
            new IllegalAccessException("삭제할 수 있는 권한이 없습니다.");
        }
    }

    // 자녀 정보 가져와서 체질량지수(BMI) 계산
    public BMIResponse calculateChildrenBMI(String childrenName) {
        // 등록된 자녀 유무 확인 과정
        Children children = childrenRepository.findByName(childrenName)
                .orElseThrow(() -> new IllegalArgumentException("해당 자녀의 정보가 존재하지 않습니다."));

        // 저장되어 있는 자녀 정보 가져와서 BMI 계산
        double height = children.getHeight() * 0.01;
        double bmi = Math.round((children.getWeight() / (height * height)) * 100.0 / 100.0);

        String bmiFeedback = feedBackService.getBMIFeedback(bmi);
        return new BMIResponse(bmi, bmiFeedback);
    }

    // 정보를 가져오지 않고 입력해서 체질량지수(BMI) 계산
    public BMIResponse calculateBMI(BMICalculateRequest bmiCalculateRequest) {
        double height = bmiCalculateRequest.getHeight() * 0.01;
        double bmi = Math.round(bmiCalculateRequest.getWeight() / (height * height)) * 100.0 / 100.0;

        String bmiFeedback = feedBackService.getBMIFeedback(bmi);
        return new BMIResponse(bmi, bmiFeedback);
    }

    // 해당 부모의 등록되어 있는 자녀 리스트 가져오기
    public List<Children> getChildrenList(String memberNumber) {
        // member 존재 확인
        memberRepository.findByPhoneNumber(memberNumber)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        List<Children> children = childrenRepository.findChildrenByPhoneNumber(memberNumber);
        return children;

    }
}

