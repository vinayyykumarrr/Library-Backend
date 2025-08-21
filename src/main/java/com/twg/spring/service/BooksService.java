package com.twg.spring.service;
import java.util.List;
import com.twg.spring.entity.Books;

public interface BooksService 
{
	public Books addBook(Books books);
	public Books updateBook(int id,Books books);
	public void deleteBook(int id);
	public Books getBookById(int id);
	public List<Books> getAllBooks();
}
