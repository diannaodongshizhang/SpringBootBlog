package com.yrx.service;

import java.util.List;

import com.yrx.po.Comment;

public interface CommentService {
	
	List<Comment> listCommentById(Long blogId);
	
	Comment saveComment(Comment comment);
}
