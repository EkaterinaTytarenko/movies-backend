package com.practice.doesthecatdie.repositories;

import com.practice.doesthecatdie.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

    public boolean existsByName(String name);
}
