package com.yrx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yrx.dao.UserRepository;
import com.yrx.po.User;
import com.yrx.util.MD5Utils;
@Service
public class UserServiceImpl implements UserService{
		
	@Autowired
	private UserRepository userRepository;
	@Override
	public User checkUser(String username,String password) {
		User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
		
		return user;
	}
}
