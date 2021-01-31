package com.myproject.persondemo;

import com.myproject.persondemo.controller.Delegater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonDemoApplication implements CommandLineRunner {
	@Autowired
	Delegater delegate;
	public static void main(String[] args) {
		SpringApplication.run(PersonDemoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		delegate.options();
	}
}
