package com.laptrinhjavaweb.controller;

import com.laptrinhjavaweb.DTO.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.UserService;

public class UserController {

	public static void main(String[] args) {
		IUserService userService = new UserService();
		for(UserDTO user : userService.findAll()) {
			System.out.println(user.getUserName()+"  "+user.getFullName());
		}
	}
}
