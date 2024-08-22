package com.bookms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookms.entity.BookMsEntity;

public interface BookMsRepo extends JpaRepository<BookMsEntity, Integer> {

}
