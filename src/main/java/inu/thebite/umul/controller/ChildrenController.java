package inu.thebite.umul.controller;


import inu.thebite.umul.domain.Children;
import inu.thebite.umul.model.BMICalculateRequest;
import inu.thebite.umul.model.BMIResponse;
import inu.thebite.umul.model.children.ChildrenResponse;
import inu.thebite.umul.model.children.ChildrenSaveRequest;
import inu.thebite.umul.model.children.ChildrenUpdateRequest;
import inu.thebite.umul.services.ChildrenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChildrenController {

    private final ChildrenService childrenService;

    // 자녀 정보 등록
    @PostMapping("{memberNumber}/children")
    public ResponseEntity saveChildren(@PathVariable String memberNumber,
                                       @RequestBody ChildrenSaveRequest childrenSaveRequest) {
        Children children = childrenService.saveChildren(memberNumber, childrenSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(children);
    }

    // 자녀 정보 수정
    @PatchMapping("{childrenName}/children")
    public ResponseEntity updateChildren(@PathVariable String childrenName,
                                         @RequestBody ChildrenUpdateRequest childrenUpdateRequest) {
        Children children = childrenService.updateChildren(childrenName, childrenUpdateRequest);
        return ResponseEntity.ok(children);
    }

    // 자녀 정보 불러오기
    @GetMapping("{childrenName}/children")
    public ResponseEntity findByChildren(@PathVariable String childrenName) {
        ChildrenResponse childrenResponse = childrenService.findByChildren(childrenName);
        return ResponseEntity.ok(childrenResponse);
    }

    // 자녀 정보 삭제
    @DeleteMapping("{childrenName}/children/{memberNumber}")
    public ResponseEntity deleteChildren(@PathVariable String childrenName,
                                         @PathVariable String memberNumber) {
        childrenService.deleteChildren(childrenName, memberNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 해당 자녀가 사용할 타니얌 등록과정을 여기에?

    // 자녀 정보 가쟈와서 체질량지수(BMI) 계산
    @GetMapping("{childrenName}/children/bmi")
    public ResponseEntity getChildrenBmi(@PathVariable String childrenName) {
        BMIResponse bmiResponse = childrenService.calculateChildrenBMI(childrenName);
        return ResponseEntity.ok(bmiResponse);
    }

    // 정보를 가져오지 않고 입력해서 체질량지수(BMI) 계산
    @GetMapping("calculate/bmi")
    public ResponseEntity getCalculateBmi(@RequestBody BMICalculateRequest bmiCalculateRequest) {
        BMIResponse bmiResponse = childrenService.calculateBMI(bmiCalculateRequest);
        return ResponseEntity.ok(bmiResponse);
    }

    // 저장되어 있는 자녀리스트 가져오기
    @GetMapping("{memberNumber}/children/list")
    public List<Children> getChildrenList(@PathVariable String memberNumber) {
        List<Children> result = childrenService.getChildrenList(memberNumber);
        return result;
    }
}
