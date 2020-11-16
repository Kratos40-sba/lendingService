package com.loanlander.lending_engine.domain.repository;

import com.loanlander.lending_engine.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
