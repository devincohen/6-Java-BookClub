package com.codingdojo.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
	
	//get all books
	public List<Book> allBooks(){
		return bookRepo.findAll(); 
	}
	
	//find book by id
	public Book findBookById(Long id) {
		Optional<Book> potentialBook = bookRepo.findById(id);
		if(potentialBook.isPresent()) {
			return potentialBook.get();
		}
		return null;
	}
	
	
	//Create, Update, Delete books
	
	public Book createBook(Book b) {
		return bookRepo.save(b);
	}
	
	public Book updateBook(Book b) {
		return bookRepo.save(b);
	}
	
	public void deleteBook(Book b) {
		bookRepo.delete(b);
	}

}
