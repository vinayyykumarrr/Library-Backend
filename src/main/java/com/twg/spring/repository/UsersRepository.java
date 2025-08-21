package com.twg.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.twg.spring.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>
{
	
	Users findByEmail(String email);
}
