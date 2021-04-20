package com.tht.demo.service.impl;

import com.tht.demo.model.Blog;
import com.tht.demo.repository.BlogRepository;
import com.tht.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;


    @Override
    public Page<Blog> showAll(Pageable pageable) {
        return blogRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        blogRepository.softDeleteBlog(id);
    }
}
