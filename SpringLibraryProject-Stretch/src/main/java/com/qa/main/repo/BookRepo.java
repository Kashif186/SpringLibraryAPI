package com.qa.main.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qa.main.domain.Book;
import com.qa.main.domain.Person;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
	
	@Query(value = "SELECT * FROM Book WHERE title LIKE '%?1%'", nativeQuery =true)
	List<Book> findByTitle(String title);
	
	@Query(value = "SELECT * FROM Book WHERE author=?1", nativeQuery =true)
	List<Book> findByAuthor(String author);
	
	@Query(value = "SELECT * FROM Book WHERE total_pages<=?1", nativeQuery =true)
	List<Book> findBooksWithPagesLessThan(int pages);
	
	@Query(value = "SELECT * FROM Book WHERE total_pages>=?1", nativeQuery =true)
	List<Book> findBooksWithPagesGreaterThan(int pages);
	
	@Query(value = "SELECT * FROM Book WHERE person_id=?1", nativeQuery =true)
	List<Book> findBooksByPerson(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Book SET person_id=?2 WHERE id=?1", nativeQuery =true)
	int loanBook(Long bookId, Person p);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Book SET person_id=null WHERE id=?1", nativeQuery =true)
	int returnBook(Long bookId);
	
	

}
