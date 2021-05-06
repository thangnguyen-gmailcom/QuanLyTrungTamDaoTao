package com.tht.demo.service;

import com.tht.demo.model.Attend;
import com.tht.demo.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogService {
    Page<Blog> showAll(Pageable pageable);

    Optional<Blog> findById(Long id);

    Blog save(Blog blog);

    void delete(Long id);

    Page<Blog> findAllByTitleContainingAndDeletedIsFalse(String title, Pageable pageable);
}
