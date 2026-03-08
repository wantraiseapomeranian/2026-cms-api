package com.malgn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malgn.entity.Contents;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long> {
	
	
}
