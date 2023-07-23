package inu.thebite.umul.controller;


import inu.thebite.umul.domain.TinyYam;
import inu.thebite.umul.model.TinyYamRegisterRequest;
import inu.thebite.umul.services.TinyYamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TinyYamController {

    private final TinyYamService tinyYamService;

    // 타니얌 등록
    @PostMapping("/{memberNumber}/tinyYam")
    public ResponseEntity<TinyYam> registerTinyYam(@PathVariable String memberNumber,
                                                @RequestBody TinyYamRegisterRequest tinyYamRegisterRequest) {
        TinyYam tinyYam = tinyYamService.registerTinyYam(memberNumber, tinyYamRegisterRequest);
        return ResponseEntity.ok(tinyYam);
    }
}
