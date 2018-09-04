package ren.xiangmu.iiwx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ren.xiangmu.iiwx.controller.RemoteDateController;
import ren.xiangmu.iiwx.entity.Wx_user;

@Mapper
@Repository
public interface Wx_userMapper {
	
	Wx_user getOne(Integer id);

	int insert(Wx_user wx_user);

	void updateByPrimaryKeyId(Wx_user wx_user);

	void delete(Integer id);
	
	int updateByPrimaryKeySelective(Wx_user record);

	public List<Wx_user> pageListByParamMap(Map param);
}
