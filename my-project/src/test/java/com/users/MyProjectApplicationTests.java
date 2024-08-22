package com.users;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.internal.Function;

@SpringBootTest
class MyProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void myFirstText() {
		Function<Integer,String> function1 = (x) -> x.toString();
		Function<String,String> function2 = String::trim;
		Function<String,String> function3 = String::toUpperCase;

		System.out.println("My first Test!!!! ::::::::: " + function1.apply(7777777));
		System.out.println("-----------> " + function2.apply("works!!!"));
	}

}
