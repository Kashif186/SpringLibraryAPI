package com.qa.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.main.domain.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
	
	@Query(value = "SELECT * FROM Book WHERE title=?1", nativeQuery =true)
	List<Book> findBytitle(String title);
	
	@Query(value = "SELECT * FROM Book WHERE author=?1", nativeQuery =true)
	List<Book> findByAuthor(String author);
	
	@Query(value = "SELECT * FROM Book WHERE total_pages<=?1", nativeQuery =true)
	List<Book> findBooksWithPagesLessThan(int pages);
	
	@Query(value = "SELECT * FROM Book WHERE total_pages>=?1", nativeQuery =true)
	List<Book> findBooksWithPagesGreaterThan(int pages);
	
	@Query(value = "SELECT * FROM Book WHERE person_id=?1", nativeQuery =true)
	List<Book> findBooksByPerson(Long id);
	
	

}
