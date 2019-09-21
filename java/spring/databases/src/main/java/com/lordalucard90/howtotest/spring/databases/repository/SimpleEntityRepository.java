package com.lordalucard90.howtotest.spring.databases.repository;


import com.lordalucard90.howtotest.spring.databases.entity.SimpleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SimpleEntityRepository extends JpaRepository<SimpleEntity, Long> {
}
