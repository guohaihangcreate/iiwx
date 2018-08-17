package ren.xiangmu.iiwx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import ren.xiangmu.iiwx.entity.Wx_user;
import ren.xiangmu.iiwx.mapper.UserMapper;
import ren.xiangmu.iiwx.service.WxUserService;

@Service
@ServletComponentScan(basePackages = "ren.*.*.servlet")
public class WxUserServiceImpl implements WxUserService {

	public WxUserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<Wx_user> pageListByParamMap(Map param) {
		// TODO Auto-generated method stub
		return userMapper.pageListByParamMap(param);
	}

	@Override
	public int updateByPrimaryKeySelective(Wx_user wx_user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(wx_user);
	}

	@Override
	public Wx_user getOne(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.getOne(id);
	}

	@Override
	public void insert(Wx_user wx_user) {
		// TODO Auto-generated method stub
		 userMapper.insert(wx_user);
	}

}
