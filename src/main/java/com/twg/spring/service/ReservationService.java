package com.twg.spring.service;
import java.util.List;
import com.twg.spring.entity.Reservation;

public interface ReservationService 
{
	public Reservation createReservation(Reservation reservation);
	public List<Reservation> getReservationsByUserId(int userId);
	public List<Reservation> getAllReservations();
	public Reservation updateReservation(int id, Reservation updatedReservation);
	public void deleteReservation(int id);
}


