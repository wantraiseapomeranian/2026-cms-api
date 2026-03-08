package com.malgn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malgn.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	
	
}
