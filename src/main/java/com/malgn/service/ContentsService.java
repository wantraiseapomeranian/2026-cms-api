package com.malgn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.malgn.entity.Contents;
import com.malgn.entity.MemberLevel;
import com.malgn.repository.ContentsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentsService {
	
	//레포지토리 연결
	private final ContentsRepository contentsRepository;
	
	//컨텐츠 추가
	@Transactional
	public Contents createContents(String title, String description, String loginId) {
        Contents contents = Contents.builder()
                .title(title)
                .description(description)
                .createdBy(loginId)
                .build();
        return contentsRepository.save(contents);
    }
	
	//목록 조회
	@Transactional(readOnly = true)
    public Page<Contents> getContentsList(Pageable pageable) {
        return contentsRepository.findAll(pageable);
    }
	
	//상세 조회
	@Transactional
	public Contents getContentsDetail(Long id) {
		Contents contents = contentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. ID: " + id));
        
        //조회수 증가
        contents.increaseViewCount(); 
        
        return contents;
	}
	
	//컨텐츠 수정
	@Transactional
    public Contents updateContents(Long id, String title, String description, String loginId, MemberLevel memberLevel) {
        Contents contents = contentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. ID: " + id));

        // 권한 확인
        if (!contents.getCreatedBy().equals(loginId) && memberLevel != MemberLevel.ROLE_ADMIN) {
            throw new IllegalArgumentException("게시글을 수정할 권한이 없습니다.");
        }

        //수정일 / 수정자 갱신
        contents.update(title, description, loginId);
        return contents;
    }
	
	//컨텐츠 삭제
	@Transactional
    public void deleteContents(Long id, String loginId, MemberLevel memberLevel) {
        Contents contents = contentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. ID: " + id));

        // 권한 확인
        if (!contents.getCreatedBy().equals(loginId) && memberLevel != MemberLevel.ROLE_ADMIN) {
            throw new IllegalArgumentException("게시글을 삭제할 권한이 없습니다.");
        }
        
        //컨텐츠 삭제하기
        contentsRepository.delete(contents);
    }
}





