package com.twg.spring.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twg.spring.entity.Books;
import com.twg.spring.repository.BooksRepository;

@Service
public class BooksServiceImpl implements BooksService 
{
	@Autowired
	private BooksRepository booksRepository;
	@Override
	public Books addBook(Books books) {
		return booksRepository.save(books);
	}

	@Override
	public Books updateBook(int id, Books books) 
	{
		Books book=booksRepository.findById(id).orElseThrow();
			book.setTitle(books.getTitle());
			book.setAuthor(books.getAuthor());
			book.setIsbn(books.getIsbn());
			book.setAvailable(books.isAvailable());
			return booksRepository.save(book);
	}

	@Override
	public Books getBookById(int id) {
		return booksRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Books> getAllBooks() {
		return booksRepository.findAll();
	}

	@Override
	public void deleteBook(int id) 
	{
		booksRepository.deleteById(id);
	}
}

