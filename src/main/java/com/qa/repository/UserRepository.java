package com.qa.repository;

import com.qa.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User findByName(String name);
}
