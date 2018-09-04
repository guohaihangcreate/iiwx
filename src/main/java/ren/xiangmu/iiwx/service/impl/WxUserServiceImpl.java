package ren.xiangmu.iiwx.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import ren.xiangmu.iiwx.entity.Wx_user;
import ren.xiangmu.iiwx.mapper.Wx_userMapper;
import ren.xiangmu.iiwx.service.WxUserService;

@Service
@ServletComponentScan(basePackages = "ren.*.*.servlet")
public class WxUserServiceImpl implements WxUserService {
	private static final Logger LOG = LoggerFactory.getLogger(WxUserServiceImpl.class);
	public WxUserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private Wx_userMapper wx_userMapper;

	@Override
	public List<Wx_user> pageListByParamMap(Map param) {
		// TODO Auto-generated method stub
		return wx_userMapper.pageListByParamMap(param);
	}

	@Override
	public void updateByPrimaryKeyId(Wx_user wx_user) {
		// TODO Auto-generated method stub
		wx_userMapper.updateByPrimaryKeyId(wx_user);
	}

	@Override
	public Wx_user getOne(Integer id) {
		// TODO Auto-generated method stub
		return wx_userMapper.getOne(id);
	}

	@Override
	public int insert(Wx_user wx_user) {
		return wx_userMapper.insert(wx_user);
	}

}
