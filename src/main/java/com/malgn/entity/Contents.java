package com.malgn.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "contents")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Contents {
	
	//pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //컨텐츠 제목
    @Column(nullable = false, length = 100)
    private String title;
    
    
    //컨텐츠 내용
    @Column(columnDefinition = "TEXT")
    private String description;
    
    //조회수
    @Column(name = "view_count", nullable = false)
    private Long viewCount;
    
    //생성일
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;
    
    //생성자
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
    
    //수정일
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    
    //수정자
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Builder
    public Contents(String title, String description, String createdBy) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.viewCount = 0L;
    }

    // 게시글 수정 기능
    public void update(String title, String description, String lastModifiedBy) {
        this.title = title;
        this.description = description;
        this.lastModifiedBy = lastModifiedBy;
    }

    // 조회수 증가 기능
    public void increaseViewCount() {
        this.viewCount += 1;
    }
}