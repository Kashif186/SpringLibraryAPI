package com.qa.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.main.domain.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long>{

	@Query(value = "SELECT * FROM Person WHERE fine>=?1", nativeQuery =true)
	List<Person> findPeopleWithFinesGreaterThan(double number);
	
}
