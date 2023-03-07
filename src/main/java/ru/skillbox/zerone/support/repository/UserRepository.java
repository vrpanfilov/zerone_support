package ru.skillbox.zerone.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.zerone.support.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByEmail(String email);
}