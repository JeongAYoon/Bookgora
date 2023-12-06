package com.mysite.sbb;

import com.mysite.sbb.Controller.BookController;
import com.mysite.sbb.DTO.BookSearchResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private BookController bookController;
	@Test
	void testBookController() {
		String searchText = "도덕의 계보";
		List<BookSearchResponseDTO> books = bookController.searchBooks(searchText);
		BookSearchResponseDTO firstBook = books.get(0);
		System.out.println(firstBook.getImage());
	}
	@Test
	void testJpa() {
	}
}
