package com.twg.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.twg.spring.entity.Reservation;
import com.twg.spring.entity.Users;
import com.twg.spring.service.ReservationService;
import com.twg.spring.service.UsersService;

@RestController
@RequestMapping("/api/admin")
public class AdminUserController 
{
	@Autowired
	private UsersService userService;
	@Autowired
	private ReservationService reservationService;

	    @GetMapping("/users")
	    public List<Users> getAllUsers() 
	    {
	        return userService.getAllUsers();
	    }

	    @DeleteMapping("/users/{id}")
	    public void deleteUser(@PathVariable int id)
	    {
	        userService.deleteUser(id);
	    }

	    @GetMapping("/reservations")
	    public List<Reservation> getAllReservations() 
	    {
	        return reservationService.getAllReservations();
	    }
}
