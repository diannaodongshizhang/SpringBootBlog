package com.yrx.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yrx.po.Tag;


public interface TagService {
	Tag saveTag(Tag tag);
	
	Tag getTag(Long id) ;
	//分页查询
	Page<Tag> listTag(Pageable pageable);
	
	Tag updateTag(Long id,Tag tag);
	
	void deleteTag(Long id);
	
	Tag getTagByName(String name);
	
	List<Tag> listTag();
	
	List<Tag> listTag(String ids);
	
	List<Tag> listTagTop(Integer size);
}
