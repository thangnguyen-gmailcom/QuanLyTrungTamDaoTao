package com.tht.demo.repository;

import com.tht.demo.model.User;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM User WHERE is_deleted = 0 AND (role_id = 2 OR role_id = 3)", nativeQuery = true)
    Page<User> findAllEmployeeOrderByIdDesc(Pageable pageable);

    @Query(value = "SELECT * FROM User WHERE is_deleted = 0 AND role_id= 4", nativeQuery = true)
    Page<User> findAllStudentOrderByIdDesc(Pageable pageable);

    @Query(value = "SELECT * FROM User WHERE is_deleted = 0 AND role_id= 2", nativeQuery = true)
    Page<User> findAllTeacherOrderByIdDesc(Pageable pageable);

    @Query(value = "SELECT * FROM User WHERE role_id = 4 AND is_Deleted = 0 AND id = :id",nativeQuery = true)
    Optional<User> findStudentById(@Param("id") Long id);

    @Query(value = "SELECT * FROM User WHERE is_deleted = 0 AND (role_id = 2 OR role_id = 3) AND  id = :id",nativeQuery = true)
    Optional<User> findStaffById(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE User u SET u.password = :password WHERE u.id = :id ")
    void updatePassword(@Param("password") String password, @Param("id") Long id);

    Optional<User> findByPhoneNumber(String phone);

    Optional<User> findByIdCard(String idCard);

    @Modifying
    @Query(value = "UPDATE User u SET u.isDeleted = 1 WHERE u.id = :id")
    void deletedUser(@Param("id") Long id);
}
