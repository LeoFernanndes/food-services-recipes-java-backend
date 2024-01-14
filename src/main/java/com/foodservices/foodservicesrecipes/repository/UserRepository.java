package com.foodservices.foodservicesrecipes.repository;

import com.foodservices.foodservicesrecipes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
