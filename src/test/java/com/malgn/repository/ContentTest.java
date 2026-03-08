package com.malgn.repository;

import com.malgn.entity.Contents;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ContentsTest {

    @Autowired
    private ContentsRepository contentsRepository;

    @Test
    @DisplayName("게시글 저장 및 상세 조회 테스트")
    void saveAndFindTest() {
        //데이터
        Contents content = Contents.builder()
                .title("테스트 제목")
                .description("테스트 내용입니다.")
                .createdBy("admin")
                .build();

        //실행
        Contents savedContent = contentsRepository.save(content);
        Contents foundContent = contentsRepository.findById(savedContent.getId()).orElse(null);

        //검증
        assertThat(foundContent).isNotNull();
        assertThat(foundContent.getTitle()).isEqualTo("테스트 제목");
        assertThat(foundContent.getViewCount()).isEqualTo(0);
    }
}