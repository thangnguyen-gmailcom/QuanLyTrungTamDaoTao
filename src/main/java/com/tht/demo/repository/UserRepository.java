package com.tht.demo.repository;

import com.tht.demo.model.User;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM User WHERE is_deleted = 0 AND role_id = 2", nativeQuery = true)
    Page<User> findAllEmployeeOrderByIdDesc(Pageable pageable);

    Optional<User> findByPhoneNumber(String phone);

    Optional<User> findByIdCard(String idCard);
}
