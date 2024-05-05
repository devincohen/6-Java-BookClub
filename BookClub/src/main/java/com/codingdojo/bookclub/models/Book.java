package com.codingdojo.bookclub.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name="books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank (message="Title is required!")
	@Size(min=1, max=50, message="Title must be between 1 and 50 characters Long.")
	private String title;
	
	@NotBlank(message="Author is required!")
	@Size(min=1, max=100, message="Author must be between 1 and 100 characters Long.")
	private String author;
	
	@NotBlank (message="Thoughts are required!")
	@Size(min=3, message="Thoughts must be at least 3 characters long")
	private String thoughts;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	public Book() {}
	
//	PUBLIC BOOK(STRING TITLE, STRING AUTHOR, STRING THOUGHTS) {
//		THIS.TITLE = TITLE;
//		THIS.AUTHOR = AUTHOR;
//		THIS.THOUGHTS = THOUGHTS;
//	}
	
	

	   // getters & setters
	 @PrePersist
	 protected void onCreate() {
	 		this.createdAt = new Date();
	 }
	 @PreUpdate
	 protected void onUpdate() {
	 	this.updatedAt = new Date();
	 }
	 

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the thoughts
	 */
	public String getThoughts() {
		return thoughts;
	}

	/**
	 * @param thoughts the thoughts to set
	 */
	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
