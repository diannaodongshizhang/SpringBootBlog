package com.yrx.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yrx.po.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{
	
	
	

	List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
