package com.yrx.service;

import com.yrx.po.User;

public interface UserService {
	User checkUser(String username,String password);
}
