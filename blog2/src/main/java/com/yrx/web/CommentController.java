package com.yrx.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yrx.dao.CommentRepository;
import com.yrx.po.Comment;
import com.yrx.po.User;
import com.yrx.service.BlogService;
import com.yrx.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private BlogService blogService;
	
	@Value("${comment.avatar}")
	private String avatar;
	@GetMapping("/comments/{blogId}")
	public String comments(@PathVariable Long blogId,Model model) {
		model.addAttribute("comments",commentService.listCommentById(blogId));
		
		
		return  "blog :: commentList";
	}
	@PostMapping("/comments")
	public String  post(Comment comment,HttpSession session) {
		
		Long blogid=comment.getBlog().getId();
		comment.setBlog(blogService.getBlog(blogid));
		
		
		
		User user=(User)session.getAttribute("user");
		if(user!=null) {
			comment.setAvatar(user.getAvatar());
			comment.setAdminComment(true);
	//		comment.setNickname(user.getNickname());
		}else {
			comment.setAvatar(avatar);
		}
		commentService.saveComment(comment);
		return "redirect:/comments/" + comment.getBlog().getId();
	}
}
