package ren.xiangmu.iiwx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ren.xiangmu.iiwx.entity.User;
import ren.xiangmu.iiwx.mapper.UserMapper;

@Controller
public class HomeController {

	@RequestMapping("/home")
	@ResponseBody
	public String getHome() {

		return "hello,spring-boot!!!";
	}

	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/getUsers")
	public String getUsers(Model model) {
		List<User> users = userMapper.getAll();
		model.addAttribute("users", users);
		return "user/userList";
	}
}
