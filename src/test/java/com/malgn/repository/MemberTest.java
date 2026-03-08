package com.malgn.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.entity.Member;
import com.malgn.entity.MemberLevel;

@SpringBootTest
@Transactional
public class MemberTest {
	
	@Autowired
    private MemberRepository memberRepository;
	
    @Test
    @DisplayName("유저 조회")
    void findById_Test() {
        //데이터
        String testId = "testUser";
        Member member = Member.builder()
                .memberId(testId)
                .memberPw("password123")
                .memberLevel(MemberLevel.ROLE_USER)
                .build();
        
        // DB에 저장
        memberRepository.save(member);

        //메서드 실행
        Member foundMember = memberRepository.findById(testId).orElse(null);

        //검증
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getMemberId()).isEqualTo(testId);
        assertThat(foundMember.getMemberLevel()).isEqualTo(MemberLevel.ROLE_USER);
    }
}
