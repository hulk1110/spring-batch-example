package com.nishh.springbatchexample1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishh.springbatchexample1.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
