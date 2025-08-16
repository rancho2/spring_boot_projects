package com.example.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Category;

public interface CategoryRepo extends JpaRepository<Category, String>{

}
