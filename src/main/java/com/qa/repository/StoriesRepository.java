package com.qa.repository;

import com.qa.domain.Stories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoriesRepository extends JpaRepository<Stories, Long> {
    Stories findByName(String name);
}
