package com.blogging_platform_api.repository;

import com.blogging_platform_api.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByAuthorId(Long userId);
    List<Blog> findByCategories_IdOrderByCreatedAtDesc(Long categoryId);
    @Query("SELECT COUNT(u) FROM Blog b JOIN b.likedByUsers u WHERE b.id = :blogId")
    long countLikesByBlogId(@Param("blogId") Long blogId);
}
