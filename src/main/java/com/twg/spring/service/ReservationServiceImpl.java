package com.twg.spring.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twg.spring.entity.Books;
import com.twg.spring.entity.Reservation;
import com.twg.spring.entity.Users;
import com.twg.spring.repository.BooksRepository;
import com.twg.spring.repository.ReservationRepository;
import com.twg.spring.repository.UsersRepository;
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BooksRepository booksRepository;
    
    @Override
    public Reservation createReservation(Reservation reservation) {
    	 Users user = usersRepository.findById(reservation.getUsers().getId()).orElseThrow();
         Books book = booksRepository.findById(reservation.getBooks().getId()).orElseThrow();

         reservation.setUsers(user);
         reservation.setBooks(book);

         return reservationRepository.save(reservation);
    }
    
	@Override
	public List<Reservation> getReservationsByUserId(int userId) 
	{
		 Users user = usersRepository.findById(userId).orElseThrow();
	        return reservationRepository.findByUsers(user);
	}
	
	@Override
	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}
	
	@Override
	public Reservation updateReservation(int id, Reservation updatedReservation) 
	{
		 Reservation reservation = reservationRepository.findById(id).orElseThrow();

	        reservation.setIssue_date(updatedReservation.getIssue_date());
	        reservation.setReturn_date(updatedReservation.getReturn_date());
	        reservation.setStatus(updatedReservation.getStatus());
	        return reservationRepository.save(reservation);
	}
	
	@Override
	public void deleteReservation(int id) 
	{
		reservationRepository.deleteById(id);
	}
}


