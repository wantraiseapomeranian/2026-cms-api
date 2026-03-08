package com.malgn.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.entity.Contents;
import com.malgn.entity.MemberLevel;
import com.malgn.service.ContentsService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentsController {
	
	//Service 주입
	private final ContentsService contentsService;
	
	//DTO 정의
	@Data
    public static class ContentsRequest {
        private String title;
        private String description;
    }
	
	//컨텐츠 추가
    @PostMapping
    public ResponseEntity<Contents> createContents(
            @RequestBody ContentsRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        //시큐리티를 통해 현재 로그인한 사용자의 아이디를 꺼내옵니다.
        String loginId = userDetails.getUsername(); 
        Contents created = contentsService.createContents(request.getTitle(), request.getDescription(), loginId);
        
        return ResponseEntity.ok(created);
    }

    //컨텐츠 목록 조회
    @GetMapping
    public ResponseEntity<Page<Contents>> getContentsList(
            @PageableDefault(size = 10, sort = "createdDate") Pageable pageable) {
        
        return ResponseEntity.ok(contentsService.getContentsList(pageable));
    }

    //컨텐츠 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<Contents> getContentsDetail(@PathVariable Long id) {
        return ResponseEntity.ok(contentsService.getContentsDetail(id));
    }

    //컨텐츠 수정
    @PutMapping("/{id}")
    public ResponseEntity<Contents> updateContents(
            @PathVariable Long id,
            @RequestBody ContentsRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        String loginId = userDetails.getUsername();
        MemberLevel memberLevel = extractRole(userDetails);
        
        Contents updated = contentsService.updateContents(id, request.getTitle(), request.getDescription(), loginId, memberLevel);
        return ResponseEntity.ok(updated);
    }

    //컨텐츠 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContents(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        String loginId = userDetails.getUsername();
        MemberLevel memberLevel = extractRole(userDetails);
        
        contentsService.deleteContents(id, loginId, memberLevel);
        return ResponseEntity.ok().build();
    }

    //권한 추출을 위한 메서드
    private MemberLevel extractRole(UserDetails userDetails) {
        String authority = userDetails.getAuthorities().iterator().next().getAuthority();
        return MemberLevel.valueOf(authority);
    }
}





