package com.blogging_platform_api.repository;

import com.blogging_platform_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    boolean existsById(Long id);

    Category findByNameIgnoreCase(String name);

    List<Category> findAllByOrderByCreatedAtDesc();
}
