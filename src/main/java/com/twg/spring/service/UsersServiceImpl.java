package com.twg.spring.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.twg.spring.entity.Users;
import com.twg.spring.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	private PasswordEncoder passwordEncoder;
	@Override
	public Users saveUser(Users users) 
	{
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		return usersRepository.save(users);
	}
	
	 @Override
	    public Users updateUser(int id, Users users) 
	 {
	        Users user = usersRepository.findById(id).orElseThrow();
	        
	            user.setName(users.getName());
	            user.setEmail(users.getEmail());
	            user.setRole(users.getRole());
	            if(!users.getPassword().isBlank())
	            {
	            	user.setPassword(passwordEncoder.encode(users.getPassword()));
	            }
	            return usersRepository.save(user);
	    }
	@Override
	public Users findById(int id) {
		return usersRepository.findById(id).orElseThrow();
	}

	@Override
	public void deleteUser(int id) {
		usersRepository.deleteById(id);
	}

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}
}


