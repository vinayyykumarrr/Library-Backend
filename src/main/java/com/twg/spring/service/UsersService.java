package com.twg.spring.service;
import java.util.List;
import com.twg.spring.entity.Users;

public interface UsersService 
{
	public Users saveUser(Users users);
	public Users updateUser(int id,Users users);
	public Users findById(int id);
	public void deleteUser(int id);
	public List<Users> getAllUsers();
}
