package com.yrx.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yrx.po.Type;

public interface TypeRepository extends JpaRepository<Type,Long> {


	Type findByName(String name);

	//分页大小作为查的条数
	@Query("select t from Type t")
	List<Type> findTop(Pageable pageable);
	
}
