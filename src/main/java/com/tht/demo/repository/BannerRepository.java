package com.tht.demo.repository;


import com.tht.demo.model.Banner;
import com.tht.demo.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BannerRepository extends JpaRepository<Banner,Long> {

    @Modifying
    @Query("update Banner b set b.deleted = true where b.id = :id")
    void softDelete(@Param("id") Long id);


    Page<Banner> findAllByDeletedIsFalse(Pageable pageable);


    Page<Banner> findTop3ByDeletedIsFalseOrderByIdDesc(Pageable pageable);

    Page<Banner> findAllByDescriptionContainingAndDeletedIsFalse(String description, Pageable pageable);
 }
