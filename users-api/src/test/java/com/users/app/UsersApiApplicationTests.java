package com.users.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;

@SpringBootTest
class UsersApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void firstTest() {
		Function<Integer,String> function1 = (x) -> x.toString();

		System.out.println("First Test!!!! " + function1.apply(777777777));
	}

	@Test
	public void otherTestMunar() {

		Function<String,String> f1 = String::toLowerCase;
		Function<String,String> f2 = String::toUpperCase;
		Function<String,String> f3 = String::trim;

		Function<String, String> f4 = f1.andThen(f2).andThen(f3);

		System.out.println(f4.apply("     Hello World"));
	}

}
