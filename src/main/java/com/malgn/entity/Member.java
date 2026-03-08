package com.malgn.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	
	//아이디
	@Id
    @Column(name = "member_id", length = 50)
    private String memberId;
	
	//비밀번호
    @Column(name = "member_pw", nullable = false)
    private String memberPw;
    
    //등급
    @Enumerated(EnumType.STRING)
    @Column(name = "member_level", nullable = false, length = 20)
    private MemberLevel memberLevel;
    
    //가입 시간
    @Column(name = "member_join", nullable = false, updatable = false)
    private LocalDateTime memberJoin;
    
    //마지막 로그인 시간
    @Column(name = "member_login")
    private LocalDateTime memberLogin;

    @Builder
    public Member(String memberId, String memberPw, MemberLevel memberLevel, LocalDateTime memberJoin) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberLevel = memberLevel != null ? memberLevel : MemberLevel.ROLE_USER; 
        this.memberJoin = memberJoin != null ? memberJoin : LocalDateTime.now();
    }

    // 나중에 회원이 로그인했을 때 로그인 시간을 업데이트하기 위한 비즈니스 메서드
    public void updateLoginTime() {
        this.memberLogin = LocalDateTime.now();
    }
	
	
	
}
