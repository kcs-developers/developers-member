package com.developers.member.member.dto.request;

import com.developers.member.member.entity.Member;
import com.developers.member.member.entity.Role;
import com.developers.member.member.entity.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

/**
 * 회원가입을 하기 위한 요청 DTO
 */
@Builder
@Getter
public class MemberRegisterRequest {
    @NotBlank(message = "이메일이 공백일 수 없습니다.")
    private String email;
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    private String password;
    @NotBlank(message = "사용자의 닉네임은 공백일 수 없습니다.")
    private String nickname;
    private String profileImageUrl;
    private String address;
    private String position;
    private String skills;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .type(Type.LOCAL)
                .role(Role.USER)
//                .profileImageUrl(profileImageUrl)
                .address(address)
                .position(position)
                .skills(skills)
                .point(100L)
                .build();
    }

}
