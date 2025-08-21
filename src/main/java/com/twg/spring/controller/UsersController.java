package com.twg.spring.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.twg.spring.entity.Users;
import com.twg.spring.service.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public Users registerUser(@RequestBody Users user) {
        return usersService.saveUser(user);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable int id,@RequestBody Users user) {
        return usersService.updateUser(id, user);
    }
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable int id) 
    {
        return usersService.findById(id);
    }
    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id)
    {
        usersService.deleteUser(id);
    }
}

