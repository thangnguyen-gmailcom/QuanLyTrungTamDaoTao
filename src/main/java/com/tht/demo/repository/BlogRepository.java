package com.tht.demo.repository;

import com.tht.demo.model.Blog;
import com.tht.demo.model.ExamType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlogRepository extends JpaRepository<Blog , Long> {
    @Modifying
    @Query("update Blog b set b.deleted = true where b.id = :id")
    void softDeleteBlog(@Param("id") Long id);

    Page<Blog> findAllByDeletedIsFalse(Pageable pageable);



    Page<Blog> findAllByTitleContainingAndDeletedIsFalse(String title, Pageable pageable);
}
