package ren.xiangmu.iiwx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ren.xiangmu.iiwx.entity.User;
import ren.xiangmu.iiwx.entity.Wx_user;

@Mapper
@Repository
public interface UserMapper {
	
	List<User> getAll();

	Wx_user getOne(Integer id);

	void insert(Wx_user wx_user);

	void update(Wx_user wx_user);

	void delete(Integer id);
	
	int updateByPrimaryKeySelective(Wx_user record);

	public List<Wx_user> pageListByParamMap(Map param);
}
