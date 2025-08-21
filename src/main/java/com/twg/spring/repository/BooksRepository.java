package com.twg.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.twg.spring.entity.Books;

public interface BooksRepository extends JpaRepository<Books, Integer> 
{

}
