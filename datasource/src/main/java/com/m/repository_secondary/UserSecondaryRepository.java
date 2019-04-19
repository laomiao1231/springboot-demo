package com.m.repository_secondary;

import com.m.entity_primary.User;
import com.m.entity_secondary.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecondaryRepository extends JpaRepository<Demo, Integer> {

}
