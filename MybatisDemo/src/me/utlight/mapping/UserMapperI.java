package me.utlight.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import me.utlight.entity.User;

//定义sql映射的接口 使用注解的方式
public interface UserMapperI {
	
	@Insert("insert into users(name,age) values(#{name},#{age})")
	public int add(User user);
	
	@Delete("delete from users where id=#{id}")
	public int deleteById(int id);
	
	@Update("update users set name=#{name},age=#{age} where id=#{id}")
	public int update(User user);
	
	@Select("select * from users")
	public List<User> getAll();
}
