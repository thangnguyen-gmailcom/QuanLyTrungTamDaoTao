package com.tht.demo.repository;

import com.tht.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog , Long> {
}
