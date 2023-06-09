package com.developers.member.point.controller;

import com.developers.member.point.dto.request.MemberPointRequest;
import com.developers.member.point.dto.response.GetPointRankingResponse;
import com.developers.member.point.dto.response.MemberPointResponse;
import com.developers.member.point.service.PointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member/point")
public class PointController {
    private final PointService pointService;

    /**
     * 점수 적립
     * @param request 사용자의 PK 번호
     * @return MemberPointResponse
     */
    @PatchMapping("/increase")
    public ResponseEntity<MemberPointResponse> increasePoint(@Valid @RequestBody MemberPointRequest request) {
        MemberPointResponse response = pointService.increasePoint(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 점수 차감
     * @param request 사용자의 PK 번호
     * @return MemberPointResponse
     */
    @PatchMapping("/decrease")
    public ResponseEntity<MemberPointResponse> decreasePoint(@Valid @RequestBody MemberPointRequest request) {
        MemberPointResponse response = pointService.decreasePoint(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 사용자 포인트 랭킹 조회
     * @return GetPointRankingResponse
     */
    @GetMapping("/ranking")
    public ResponseEntity<GetPointRankingResponse> getPointRanking() {
        GetPointRankingResponse response = pointService.getPointRanking();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}