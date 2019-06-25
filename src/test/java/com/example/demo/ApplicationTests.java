package com.example.demo;

import com.light.springboot.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ApplicationTests {

	@Test
	public void contextLoads() {
		class Test{
			public String name;
		}
		ArrayList<String> users = new ArrayList<>(2);
		users.add("s");
		System.out.println("test before null");
		String nullStr = null;
		System.out.println("Set nullStr");
		System.out.println(nullStr);
		System.out.println("after nulll");
		Test test = null;
		System.out.println(test);
		System.out.println(test+"after nulll");
		System.out.println(test.name);
		System.out.println(test+"after nulll");
	}



}
