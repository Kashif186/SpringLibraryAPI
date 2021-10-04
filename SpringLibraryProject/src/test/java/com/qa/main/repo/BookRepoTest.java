package com.qa.main.repo;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.main.domain.Book;

@RunWith(MockitoJUnitRunner.class)
public class BookRepoTest {

	@InjectMocks
	private BookRepo repo;
	
}
