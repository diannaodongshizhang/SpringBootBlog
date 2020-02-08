package com.yrx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yrx.NotFoundException;
import com.yrx.service.BlogService;
import com.yrx.service.TagService;
import com.yrx.service.TypeService;
import com.yrx.vo.BlogQuery;

@Controller
public class IndexController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private TagService tagService;
	
	@GetMapping("/")
	public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
		model.addAttribute("page", blogService.listBlog(pageable));
		model.addAttribute("types", typeService.listTypeTop(6));
		model.addAttribute("tags", tagService.listTagTop(10));
		model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
		return "index";
	}
	@PostMapping("/search")
	public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
						 @RequestParam String query,Model model) {
		
		model.addAttribute("page", blogService.listBlog("%"+query+"%",pageable));
		model.addAttribute("query", query);
		return "search";
		
	}
	@GetMapping("/blog/{id}")
	public String blog(@PathVariable Long id,Model model) {
		model.addAttribute("blog", blogService.getAndConvert(id));
		return "blog";
	}
	@GetMapping("/footer/newblog")
	public String newblogs(Model model) {
		model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
		return "_fragments :: newblogList";
	}
	
}
