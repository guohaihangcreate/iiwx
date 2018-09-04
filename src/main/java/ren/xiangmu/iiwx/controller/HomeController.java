package ren.xiangmu.iiwx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ren.xiangmu.iiwx.entity.Wx_user;
import ren.xiangmu.iiwx.mapper.Wx_userMapper;

@Controller
public class HomeController {

	@RequestMapping("/home")
	@ResponseBody
	public String getHome() {

		return "hello,spring-boot!!!";
	}

	@Autowired
	private Wx_userMapper wx_userMapper;

	@RequestMapping("/getUsers")
	public String getUsers(Model model) {
		List<Wx_user> users = wx_userMapper.pageListByParamMap(null);
		model.addAttribute("users", users);
		return "user/userList";
	}
}
