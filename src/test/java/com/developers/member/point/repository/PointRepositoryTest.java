package com.developers.member.point.repository;

import com.developers.member.config.JpaConfig;
import com.developers.member.member.entity.Member;
import com.developers.member.member.entity.Role;
import com.developers.member.member.entity.Type;
import com.developers.member.member.repository.MemberRepository;
import com.developers.member.point.entity.Point;
import com.developers.member.point.repository.PointRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaConfig.class)
@ActiveProfiles("member-prod")
@WithMockUser(roles = "USER")
public class PointRepositoryTest {
    @Autowired private PointRepository pointRepository;
    @Autowired private MemberRepository memberRepository;


    @DisplayName("문제 풀이를 통한 포인트 적립")
    @Test
    public void increasePoint() {
        // given
        Member member = Member.builder()
                .email("testpointincrease001@kakao.com")
                .password("kakao123")
                .nickname("pointTest1")
                .type(Type.LOCAL)
                .role(Role.USER)
                .isMentor(false)
                .address("서울특별시 강남구")
                .introduce("안녕하세요 저는 ...")
                .point(100L)
                .build();
        memberRepository.save(member);
        Optional<Member> saveMember = memberRepository.findById(member.getMemberId());
        System.out.println(saveMember.get().getMemberId());
        Point point = saveMember.get().getPoint();
        point.increase(10L);
        pointRepository.save(point);

        // when
        Optional<Point> result = pointRepository.findById(point.getPointId());

        // then
        assertThat(result.get().getPoint()).isEqualTo(110L);
    }

    @DisplayName("멘토링룸 신청을 통한 포인트 차감")
    @Test
    public void decreasePoint() {
        // given
        Member member = Member.builder()
                .email("testpointdecrease002@kakao.com")
                .password("kakao123")
                .nickname("pointTest2")
                .type(Type.LOCAL)
                .role(Role.USER)
                .isMentor(false)
                .address("서울특별시 강남구")
                .introduce("안녕하세요 저는 ...")
                .point(100L)
                .build();
        memberRepository.save(member);
        Optional<Member> saveMember = memberRepository.findById(member.getMemberId());
        Point point = saveMember.get().getPoint();
        point.decrease(30L);
        pointRepository.save(point);

        // when
        Optional<Point> result = pointRepository.findById(point.getPointId());

        // then
        assertThat(result.get().getPoint()).isEqualTo(70L);
    }
}
