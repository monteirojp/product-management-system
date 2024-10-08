package com.qima.product_management_system.config;

import com.qima.product_management_system.model.Category;
import com.qima.product_management_system.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.count() == 0) { 
                categoryRepository.save(new Category(null, "Electronics", null));
                categoryRepository.save(new Category(null, "Books", null));
                categoryRepository.save(new Category(null, "Furniture", null));
                categoryRepository.save(new Category(null, "Clothing", null));
                categoryRepository.save(new Category(null, "Toys",null));
                System.out.println("Default categories seeded into the database.");
            }
        };
    }
}
