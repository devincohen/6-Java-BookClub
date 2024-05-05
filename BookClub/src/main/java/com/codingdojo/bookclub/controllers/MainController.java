package com.codingdojo.bookclub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.models.LoginUser;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.services.BookService;
import com.codingdojo.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	@Autowired
	BookService bookServ;
	
	@Autowired
	UserService userServ;
	
	@GetMapping("/")
	public String loginPage(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User newUser,
			BindingResult result, HttpSession session,
			Model model) {
		session.removeAttribute("registerErrors");
		session.removeAttribute("loginErrors");
		User user = userServ.register(newUser, result);
		if (result.hasErrors()) {
			session.setAttribute("registerErrors", result.getAllErrors());
			model.addAttribute("errors", session.getAttribute("errors"));
			model.addAttribute("newUser", new User());
			model.addAttribute("newLogin", new LoginUser());
			return "login.jsp";
		}
;		model.addAttribute("newUser", user);
		session.setAttribute("id", user.getId());
		return "redirect:/books";
	}
	
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
    		//clear session of errors
		session.removeAttribute("registerErrors");
		session.removeAttribute("loginErrors");    	
        // Add once service is implemented:
        User user = userServ.login(newLogin, result);
    
        if(result.hasErrors()) {
        		session.setAttribute("loginErrors", result.getAllErrors());
            model.addAttribute("newUser", new User());
            return "login.jsp";
        }
        model.addAttribute("user", user);
        session.setAttribute("id", user.getId());
		session.setAttribute("user", user);

        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
    
        return "redirect:/books";
    }
	
	@GetMapping("/books")
	public String books(Model model, HttpSession session) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		model.addAttribute("user", userServ.findById((Long)session.getAttribute("id")));
		List<Book> allBooks = bookServ.allBooks();
		if (!allBooks.isEmpty()) {
			model.addAttribute("books", allBooks);
		}
		return "books.jsp";
	}
	
	@GetMapping("/books/new")
	public String newBook(Model model, HttpSession session) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		//initialize placeholder book for the form
		model.addAttribute("user", userServ.findById((Long)session.getAttribute("id")));
		model.addAttribute("book", new Book());
		return "new.jsp";
	}
	
	//add a book
	@PostMapping("/addBook")
	public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session, Model model) {
		//TODO: process POST request
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		if (result.hasErrors()) {
			session.setAttribute("bookErrors", result.getAllErrors());
			model.addAttribute("book", new Book());
			return "new.jsp";
		}
		bookServ.createBook(book);
		return "redirect:/books";
	}
	
	//view book 
	@GetMapping("/books/{id}")
	public String view(Model model, HttpSession session, @PathVariable("id") Long id) {
		if (session.getAttribute("id")==null) {
			return "redirect:/login";
		}
		Book book = bookServ.findBookById(id);
		model.addAttribute("user", userServ.findById((Long)session.getAttribute("id")));
		model.addAttribute("book", book);
		return "view.jsp";
	}
	
	
	@GetMapping("/books/{id}/edit")
	public String edit(Model model, HttpSession session, @PathVariable("id") Long id) {
		model.addAttribute("book", bookServ.findBookById(id));
		model.addAttribute("user", userServ.findById((Long)session.getAttribute("id")));
		return "edit.jsp";
	}
	
	@PutMapping("/books/{id}/edit")
	public String editBook(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session, Model model) {
		if (result.hasErrors()) {
			return "edit.jsp";
		}
		bookServ.updateBook(book);
		return "redirect:/books";
	}
	
	
	
	
	//delete book
	@DeleteMapping("/books/{id}")
	public String delete(Model model, @PathVariable("id") Long id, HttpSession session) {
		if(!session.getAttribute("id").equals(id)) {
			return "redirect:/books";
		}
		bookServ.deleteBook(bookServ.findBookById(id));
		return "redirect:/books";
	}
	
	
	//logout --> destroys session and gives new place-holders for login/registration
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session){
		session.invalidate();
		model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
		return "redirect:/";
	}
	
}
