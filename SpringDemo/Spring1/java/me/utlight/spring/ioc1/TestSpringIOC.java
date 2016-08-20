package me.utlight.spring.ioc1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSpringIOC {

	@Test
	public void testStoreBook() {
		BookService service = new BookService();
		service.storeBook("spring»Î√≈");
	}

}
