package com.qa.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.main.domain.Book;


public interface BookRepo extends JpaRepository<Book, Long> {

}
