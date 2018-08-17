package ren.xiangmu.iiwx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ren.xiangmu.iiwx.entity.User;
import ren.xiangmu.iiwx.entity.Wx_user;
import ren.xiangmu.iiwx.mapper.UserMapper;

public interface WxUserService {
	
	public List<Wx_user> pageListByParamMap(Map param);
	
	int updateByPrimaryKeySelective(Wx_user wx_user);
	
	public Wx_user getOne(Integer id);
	
	public void insert(Wx_user wx_user);
	
}
