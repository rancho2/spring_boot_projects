package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.repo.CategoryRepo;

@SpringBootApplication
public class ExcelDemoApplication{
	@Autowired
    private CategoryRepo categoryRepo;
	public static void main(String[] args) {
		SpringApplication.run(ExcelDemoApplication.class, args);
	}

}
