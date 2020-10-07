package com.my.web.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.my.dto.User;
import com.my.dto.UserQueryCondition;
import com.my.expection.UserNotExistException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "user query")
	public List<User> query(UserQueryCondition userQueryCondition) {
		List<User> users = new ArrayList<>();
		users.add(new User("xia", "123"));
		users.add(new User("xia", "123"));
		users.add(new User("xia", "123"));
		return users;
	}
	
	@GetMapping(value = "/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("User id") @PathVariable String id) {
		throw new UserNotExistException(id);
		//User user = new User();
		//user.setUsername("tom");
		//return user;
	}

	@PostMapping
	public User create(@Valid @RequestBody User user, BindingResult errors) {
		System.out.println(errors);
		user.setId("1");
		return user;
	}
}
