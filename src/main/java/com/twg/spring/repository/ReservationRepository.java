package com.twg.spring.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.twg.spring.entity.Reservation;
import com.twg.spring.entity.Users;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> 
{
	List<Reservation> findByUsers(Users users);
}
